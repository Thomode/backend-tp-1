package com.example.backendtp1.services.Impl;

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
    public Estacion add(Estacion entity) {
        return null;
    }

    @Override
    public Estacion update(Estacion entity) {
        return null;
    }

    @Override
    public Estacion delete(Long aLong) {
        return null;
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
    public Estacion getEstacionMasCercana(double latitud, double longitud) {
        List<Estacion> estaciones = estacionRepository.findAll();
        Estacion estacionMasCercana = estaciones.get(0);
        double distanciaMinima = distanciaEntreDosPuntos(latitud, longitud, estacionMasCercana.getLatitud(), estacionMasCercana.getLongitud());
        for (Estacion estacion : estaciones) {
            double distancia = distanciaEntreDosPuntos(latitud, longitud, estacion.getLatitud(), estacion.getLongitud());
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                estacionMasCercana = estacion;
            }
        }
        return estacionMasCercana;
    }

    private double distanciaEntreDosPuntos(double latitud, double longitud, double latitud1, double longitud1) {
        double radioTierra = 6371; //en kilómetros
        double dLat = Math.toRadians(latitud1 - latitud);
        double dLng = Math.toRadians(longitud1 - longitud);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(latitud)) * Math.cos(Math.toRadians(latitud1));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;
        return distancia;
    }
}
