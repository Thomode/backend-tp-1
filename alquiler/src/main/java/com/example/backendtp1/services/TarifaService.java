package com.example.backendtp1.services;

import com.example.backendtp1.entities.Tarifa;

import java.util.List;

public interface TarifaService  {

    Tarifa getById(Long id);
    List<Tarifa> getAll();
}
