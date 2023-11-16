package com.example.alquiler.dtos;

import com.example.alquiler.entities.Tarifa;
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
    private Long idEstacionRetiro;
    private Long idEstacionDevolucion;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private double monto;
    private Tarifa tarifa;
}
