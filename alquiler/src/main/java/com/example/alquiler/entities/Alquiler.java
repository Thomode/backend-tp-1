package com.example.alquiler.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "ALQUILERES" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alquiler {
    @Id
    private Integer id;

    @Column(name = "ID_CLIENTE")
    private String idCliente;

    @Column(name = "ESTADO")
    private int estado;

    @Column(name = "ESTACION_RETIRO")
    private Long idEstacionRetiro;

    @Column(name = "ESTACION_DEVOLUCION")
    private Long idEstacionDevolucion;

    @Column(name = "FECHA_HORA_RETIRO")
    private LocalDateTime fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION")
    private LocalDateTime fechaHoraDevolucion;

    @Column(name = "MONTO")
    private double monto;

    @ManyToOne
    @JoinColumn(name = "ID_TARIFA")
    private Tarifa tarifa;
}
