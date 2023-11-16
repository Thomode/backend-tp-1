package com.example.alquiler.services;

import com.example.alquiler.dtos.AlquilerDTO;
import com.example.alquiler.dtos.AlquilerFinDTO;
import com.example.alquiler.dtos.AlquilerInicioDTO;
import com.example.alquiler.entities.Alquiler;

import java.util.List;

public interface AlquilerService {
    Alquiler getById(Integer id);
    List<Alquiler> getAll();
    Alquiler iniciarAlquiler(AlquilerInicioDTO alquilerInicioDTO);
    AlquilerDTO finalizarAlquiler(AlquilerFinDTO alquilerFinDTO);
    List<Alquiler> getAlquileresFinalizados(double montoInicio, double montoFin);
}
