package com.example.estacion.services;

import com.example.estacion.dtos.EstacionDTO;
import com.example.estacion.dtos.EstacionDistanciaDTO;
import com.example.estacion.entities.Estacion;

import java.util.List;

public interface EstacionService  {
    Estacion getById(Long id);
    List<Estacion> getAll();
    Estacion save(EstacionDTO estacionDTO);
    Estacion getEstacionMasCercana(double latitud, double longitud);
    Double getDistanciaEstaciones(Long idEstacionRetiro, Long idEstacionDevolucion);
}
