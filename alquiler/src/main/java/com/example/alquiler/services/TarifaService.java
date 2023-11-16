package com.example.alquiler.services;

import com.example.alquiler.entities.Tarifa;

import java.util.List;

public interface TarifaService  {

    Tarifa getById(Long id);
    List<Tarifa> getAll();
}
