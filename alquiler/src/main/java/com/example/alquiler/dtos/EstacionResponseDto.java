package com.example.alquiler.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstacionResponseDto {
    private long id;
    private String nombre;
    private LocalDateTime fechaHoraCreacion;
    private double longitud;
    private double latitud;
}
