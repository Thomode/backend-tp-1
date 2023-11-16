package com.example.backendtp1.services;

import com.example.backendtp1.dtos.EstacionDTO;
import com.example.backendtp1.dtos.EstacionDistanciaDTO;
import com.example.backendtp1.entities.Estacion;

import java.util.List;

public interface EstacionService  {
    Estacion getById(Long id);
    List<Estacion> getAll();
    Estacion save(EstacionDTO estacionDTO);
    Estacion getEstacionMasCercana(double latitud, double longitud);
    Double getDistanciaEstaciones(Long idEstacionRetiro, Long idEstacionDevolucion);
}
