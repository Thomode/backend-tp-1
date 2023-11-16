package com.example.backendtp1.services.Impl;

import com.example.backendtp1.dtos.EstacionDTO;
import com.example.backendtp1.dtos.EstacionDistanciaDTO;
import com.example.backendtp1.entities.Estacion;
import com.example.backendtp1.repositories.EstacionRepository;
import com.example.backendtp1.services.EstacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstacionServiceImpl implements EstacionService {
    private final EstacionRepository estacionRepository;

    public EstacionServiceImpl(EstacionRepository estacionRepository){
        this.estacionRepository = estacionRepository;
    }
    @Override
    public Estacion getById(Long id) {
        return estacionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Estacion> getAll() {
        return estacionRepository.findAll();
    }

    @Override
    public Estacion save(EstacionDTO estacionDTO) {
        Estacion estacion = new Estacion();
        estacion.setId(estacionRepository.getIdMaximo() + 1);
        estacion.setNombre(estacionDTO.getNombre());
        estacion.setLatitud(estacionDTO.getLatitud());
        estacion.setLongitud(estacionDTO.getLongitud());
        estacion.setFechaHoraCreacion(estacionDTO.getFechaHoraCreacion());

        return estacionRepository.save(estacion);
    }

    @Override
    public Estacion getEstacionMasCercana(double latitud, double longitud) {
        List<Estacion> estaciones = this.getAll();

        Estacion estacionMasCercana = estaciones.get(0);

        double distanciaMinima = this.calcularDistanciaEntreDosPuntosKm(latitud, longitud, estacionMasCercana.getLatitud(), estacionMasCercana.getLongitud());

        for (Estacion estacion : estaciones) {
            double distancia = this.calcularDistanciaEntreDosPuntosKm(latitud, longitud, estacion.getLatitud(), estacion.getLongitud());

            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                estacionMasCercana = estacion;
            }
        }
        return estacionMasCercana;
    }

    @Override
    public Double getDistanciaEstaciones(Long idEstacionRetiro, Long idEstacionDevolucion) {
        Estacion estacionRetiro = this.getById(idEstacionRetiro);

        if(estacionRetiro == null){
            throw new RuntimeException("Estacion retiro no encontrada");
        }

        Estacion estacionDevolucion = this.getById(idEstacionDevolucion);

        if(estacionDevolucion == null){
            throw new RuntimeException("Estacion devolucion no encontrada");
        }

        return this.calcularDistanciaEntreDosPuntosKm(
                estacionRetiro.getLatitud(),
                estacionRetiro.getLongitud(),
                estacionDevolucion.getLatitud(),
                estacionDevolucion.getLongitud());
    }

    private double calcularDistanciaEntreDosPuntosKm(double latitud, double longitud, double latitud1, double longitud1) {
        double radioTierra = 6371; //en kilómetros
        double dLat = Math.toRadians(latitud1 - latitud);
        double dLng = Math.toRadians(longitud1 - longitud);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(latitud)) * Math.cos(Math.toRadians(latitud1));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        return radioTierra * va2;
    }
}
