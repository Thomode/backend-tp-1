package com.example.backendtp1.dtos;

import com.example.backendtp1.entities.Estacion;
import com.example.backendtp1.entities.Tarifa;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerDTO {
    private Integer id;
    private String idCliente;
    private int estado;
    private Estacion estacionRetiro;
    private Estacion estacionDevolucion;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private double monto;
    private Tarifa tarifa;
}
