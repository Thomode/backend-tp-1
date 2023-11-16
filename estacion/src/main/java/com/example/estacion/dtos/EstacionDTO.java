package com.example.estacion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstacionDTO {
    private String nombre;
    private LocalDateTime fechaHoraCreacion;
    private double latitud;
    private double longitud;
}
