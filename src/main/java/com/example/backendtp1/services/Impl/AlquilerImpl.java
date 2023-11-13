package com.example.backendtp1.services.Impl;

import com.example.backendtp1.dtos.AlquilerDTO;
import com.example.backendtp1.entities.Alquiler;
import com.example.backendtp1.entities.Estacion;
import com.example.backendtp1.repositories.AlquilerRepository;
import com.example.backendtp1.services.AlquilerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlquilerImpl implements AlquilerService {
    private  final AlquilerRepository alquilerRepository;

    private final EstacionServiceImpl estacionService;

    public AlquilerImpl(AlquilerRepository alquilerRepository, EstacionServiceImpl estacionService){
        this.alquilerRepository = alquilerRepository;
        this.estacionService = estacionService;
    }
    @Override
    public Alquiler add(Alquiler entity) {
        return null;
    }

    @Override
    public Alquiler update(Alquiler entity) {
        return null;
    }

    @Override
    public Alquiler delete(Integer aLong) {
        return null;
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
    public Alquiler iniciarAlquiler(int idEstacionRetiro) {
        Estacion estacionRetiro = estacionService.getById((long) idEstacionRetiro);
        if (estacionRetiro == null) {
            throw new RuntimeException("No existe la estacion");
        }
        Alquiler alquiler = new Alquiler();
        alquiler.setId(alquilerRepository.getIdMaximo() + 1);
        alquiler.setEstacionRetiro(estacionRetiro);
        alquiler.setEstado(1);
        alquiler.setFechaHoraRetiro(java.time.LocalDate.now());
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        alquiler.setIdCliente(uuidString);

        alquilerRepository.save(alquiler);
        return alquiler;
    }
    /*
    private double calcularCosto(Alquiler alquiler) {
        double costo = 0;
        double costoFijo = alquiler.getTarifa().getCostoFijo();
        double costoHora = alquiler.getTarifa().getCostoHora();
        double costoKm = alquiler.getTarifa().getCostoKm();
        double distancia = distanciaEntreDosPuntos(alquiler.getEstacionRetiro().getLatitud(), alquiler.getEstacionRetiro().getLongitud(), alquiler.getEstacionDevolucion().getLatitud(), alquiler.getEstacionDevolucion().getLongitud());
        double distanciaEnKm = distancia / 1000;
        double costoDistancia = distanciaEnKm * costoKm;
        costo = costoFijo + costoDistancia;
        return costo;
    }
    */

    @Override
    public Alquiler finalizarAlquiler(AlquilerDTO alquilerDTO) {
        Estacion estacionDevolucion = estacionService.getById(alquilerDTO.getIdEstacionDevolucion());
        if (estacionDevolucion == null) {
            throw new RuntimeException("No existe la estacion");
        }
        Alquiler alquiler =this.getById(alquilerDTO.getId());
        if (alquiler == null) {
            throw new RuntimeException("No existe el alquiler");
        }
        alquiler.setEstacionDevolucion(estacionDevolucion);
        alquiler.setEstado(0);
        alquiler.setFechaHoraDevolucion(java.time.LocalDate.now());

        alquiler.setMonto(alquilerDTO.getMonto());
        alquilerRepository.save(alquiler);
        return alquiler;
    }

}