package com.example.backendtp1.dtos;

import jakarta.persistence.Column;
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
