package com.example.estacion.services.Impl;

import com.example.estacion.dtos.*;
import com.example.estacion.entities.Estacion;
import com.example.estacion.expections.ResourseNoContentException;
import com.example.estacion.repositories.EstacionRepository;
import com.example.estacion.services.EstacionService;
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
            throw new ResourseNoContentException("Estacion retiro no encontrada");
        }

        Estacion estacionDevolucion = this.getById(idEstacionDevolucion);

        if(estacionDevolucion == null){
            throw new ResourseNoContentException("Estacion devolucion no encontrada");
        }

        return this.calcularDistanciaEntreDosPuntosKm(
                estacionRetiro.getLatitud(),
                estacionRetiro.getLongitud(),
                estacionDevolucion.getLatitud(),
                estacionDevolucion.getLongitud());
    }
    public Double calcularDistanciaEntreDosPuntosKm(Double lat1, Double lon1, Double lat2, Double lon2){
        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;
        return Math.sqrt(Math.pow(deltaLat*110000, 2) + Math.pow(deltaLon*110000, 2)) / 1000;
    }
}
