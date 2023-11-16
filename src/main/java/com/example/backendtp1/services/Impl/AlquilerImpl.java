package com.example.backendtp1.services.Impl;

import com.example.backendtp1.dtos.*;
import com.example.backendtp1.entities.Alquiler;
import com.example.backendtp1.entities.Estacion;
import com.example.backendtp1.entities.Tarifa;
import com.example.backendtp1.repositories.AlquilerRepository;
import com.example.backendtp1.services.AlquilerService;
import com.example.backendtp1.services.TarifaService;
import com.example.backendtp1.services.utils.CalculoMonto;
import com.example.backendtp1.services.utils.ConversorMoneda;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AlquilerImpl implements AlquilerService {
    private  final AlquilerRepository alquilerRepository;
    private final TarifaService tarifaService;
    private final EstacionServiceImpl estacionService;

    public AlquilerImpl(AlquilerRepository alquilerRepository, EstacionServiceImpl estacionService, TarifaService tarifaService){
        this.alquilerRepository = alquilerRepository;
        this.estacionService = estacionService;
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
        Estacion estacionRetiro = estacionService.getById(alquilerInicioDTO.getEstacion_retiro());

        if (estacionRetiro == null) {
            throw new RuntimeException("No existe la estacion");
        }

        Tarifa tarifa = tarifaService.getById(alquilerInicioDTO.getTarifaId());

        if(tarifa == null) {
            throw  new RuntimeException("No existe la tarifa");
        }

        Alquiler alquiler = new Alquiler();
        alquiler.setId(alquilerRepository.getIdMaximo() + 1);
        alquiler.setEstacionRetiro(estacionRetiro);
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
        Estacion estacionDevolucion = estacionService.getById(alquilerFinDTO.getIdEstacionDevolucion());

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

        alquiler.setEstacionDevolucion(estacionDevolucion);
        alquiler.setEstado(2);
        alquiler.setFechaHoraDevolucion(fechaHoraDevolucion);

        double monto = CalculoMonto.calcular(
                alquiler.getTarifa(),
                this.estacionService.getDistanciaEstaciones(
                        alquiler.getEstacionRetiro().getId(), alquiler.getEstacionDevolucion().getId()),
                alquiler.getFechaHoraRetiro(),
                alquiler.getFechaHoraDevolucion()
        );

        alquiler.setMonto(monto);
        alquilerRepository.save(alquiler);

        if (alquilerFinDTO.getTipoMoneda() != null) {
            ConversorMoneda conversorMoneda = new ConversorMoneda();
            MonedaRequestDto monedaRequestDto = new MonedaRequestDto(alquilerFinDTO.getTipoMoneda(), monto);
            MonedaResponseDto monedaResponseDto = conversorMoneda.convertir(monedaRequestDto);
            monto = monedaResponseDto.getImporte();
        }

        AlquilerDTO alquilerDTO = new AlquilerDTO();
        alquilerDTO.setId(alquiler.getId());
        alquilerDTO.setIdCliente(alquiler.getIdCliente());
        alquilerDTO.setEstado(alquiler.getEstado());
        alquilerDTO.setEstacionRetiro(alquiler.getEstacionRetiro());
        alquilerDTO.setEstacionDevolucion(alquiler.getEstacionDevolucion());
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