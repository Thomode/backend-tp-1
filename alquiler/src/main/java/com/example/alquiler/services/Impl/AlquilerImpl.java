package com.example.alquiler.services.Impl;

import com.example.alquiler.controllers.EstacionServiceComunicacion;
import com.example.alquiler.dtos.*;
import com.example.alquiler.entities.*;
import com.example.alquiler.repositories.AlquilerRepository;
import com.example.alquiler.services.*;
import com.example.alquiler.services.utils.CalculoMonto;
import com.example.alquiler.controllers.ConversorMonedaComunicacion;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AlquilerImpl implements AlquilerService {
    private  final AlquilerRepository alquilerRepository;
    private final TarifaService tarifaService;
    private final EstacionServiceComunicacion estacionServiceComunicacion = new EstacionServiceComunicacion();

    public AlquilerImpl(AlquilerRepository alquilerRepository, TarifaService tarifaService){
        this.alquilerRepository = alquilerRepository;
        this.tarifaService = tarifaService;
    }
    @Override
    public Alquiler getById(Integer id) {
        return alquilerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Alquiler> getAll() {
        return alquilerRepository.findAll();
    }

    @Override
    public Alquiler iniciarAlquiler(AlquilerInicioDTO alquilerInicioDTO) {
        EstacionResponseDto estacionRetiro = this.estacionServiceComunicacion.getById(alquilerInicioDTO.getEstacion_retiro());

        if (estacionRetiro == null) {
            throw new RuntimeException("No existe la estacion");
        }

        Tarifa tarifa = tarifaService.getById(alquilerInicioDTO.getTarifaId());

        if(tarifa == null) {
            throw  new RuntimeException("No existe la tarifa");
        }

        Alquiler alquiler = new Alquiler();
        alquiler.setId(alquilerRepository.getIdMaximo() + 1);
        alquiler.setIdEstacionRetiro(estacionRetiro.getId());
        alquiler.setEstado(1);
        alquiler.setFechaHoraRetiro(LocalDateTime.now());
        UUID uuid = UUID.randomUUID();
        alquiler.setIdCliente(uuid.toString());
        alquiler.setTarifa(tarifa);

        alquilerRepository.save(alquiler);

        return alquiler;
    }

    @Override
    public AlquilerDTO finalizarAlquiler(AlquilerFinDTO alquilerFinDTO) {
        EstacionResponseDto estacionDevolucion = this.estacionServiceComunicacion.getById(alquilerFinDTO.getIdEstacionDevolucion());

        if (estacionDevolucion == null) {
            throw new RuntimeException("No existe la estacion");
        }

        Alquiler alquiler = this.getById(alquilerFinDTO.getIdAlquiler());
        if (alquiler == null) {
            throw new RuntimeException("No existe el alquiler");
        }

        if(alquiler.getEstado() == 2){
            throw new RuntimeException("El alquiler esta finalizado");
        }

        LocalDateTime fechaHoraDevolucion = LocalDateTime.now();

        alquiler.setIdEstacionDevolucion(estacionDevolucion.getId());
        alquiler.setEstado(2);
        alquiler.setFechaHoraDevolucion(fechaHoraDevolucion);

        double monto = CalculoMonto.calcular(
                alquiler.getTarifa(),
                this.estacionServiceComunicacion.getDistanciaEstaciones(
                        alquiler.getIdEstacionRetiro(), alquiler.getIdEstacionDevolucion()),
                alquiler.getFechaHoraRetiro(),
                alquiler.getFechaHoraDevolucion()
        );

        alquiler.setMonto(monto);
        alquilerRepository.save(alquiler);

        if (alquilerFinDTO.getTipoMoneda() != null) {
            ConversorMonedaComunicacion conversorMonedaComunicacion = new ConversorMonedaComunicacion();
            MonedaRequestDto monedaRequestDto = new MonedaRequestDto(alquilerFinDTO.getTipoMoneda(), monto);
            MonedaResponseDto monedaResponseDto = conversorMonedaComunicacion.convertir(monedaRequestDto);
            monto = monedaResponseDto.getImporte();
        }

        AlquilerDTO alquilerDTO = new AlquilerDTO();
        alquilerDTO.setId(alquiler.getId());
        alquilerDTO.setIdCliente(alquiler.getIdCliente());
        alquilerDTO.setEstado(alquiler.getEstado());
        alquilerDTO.setIdEstacionRetiro(alquiler.getIdEstacionRetiro());
        alquilerDTO.setIdEstacionDevolucion(alquiler.getIdEstacionDevolucion());
        alquilerDTO.setFechaHoraRetiro(alquiler.getFechaHoraRetiro());
        alquilerDTO.setFechaHoraDevolucion(alquiler.getFechaHoraDevolucion());
        alquilerDTO.setMonto(monto);
        alquilerDTO.setTarifa(alquiler.getTarifa());

        return alquilerDTO;
    }

    @Override
    public List<Alquiler> getAlquileresFinalizados(double montoInicio, double montoFin) {
        return alquilerRepository.findAlquileres(montoInicio, montoFin);
    }
}