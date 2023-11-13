package com.example.backendtp1.services;

import com.example.backendtp1.entities.Estacion;

public interface EstacionService extends Service<Estacion, Long> {
    Estacion getEstacionMasCercana(double latitud, double longitud);
}
