package com.example.backendtp1.services;

import com.example.backendtp1.dtos.AlquilerDTO;
import com.example.backendtp1.dtos.AlquilerFiltroDTO;
import com.example.backendtp1.dtos.AlquilerFinDTO;
import com.example.backendtp1.dtos.AlquilerInicioDTO;
import com.example.backendtp1.entities.Alquiler;

import java.time.LocalDateTime;
import java.util.List;

public interface AlquilerService {
    Alquiler getById(Integer id);
    List<Alquiler> getAll();
    Alquiler iniciarAlquiler(AlquilerInicioDTO alquilerInicioDTO);
    AlquilerDTO finalizarAlquiler(AlquilerFinDTO alquilerFinDTO);
    List<Alquiler> getAlquileresFinalizados(double montoInicio, double montoFin);
}
