package com.example.alquiler.services.utils;

import com.example.alquiler.entities.Tarifa;

import java.time.Duration;
import java.time.LocalDateTime;

public class CalculoMonto {
    public static Double calcular (Tarifa tarifa,
                                       Double distanciaRecorrida,
                                       LocalDateTime fechaHoraRetiro,
                                       LocalDateTime fechaHoraDevolucion){

        Duration duration = Duration.between(fechaHoraRetiro, fechaHoraDevolucion);
        long horas = duration.toHours();
        long minutosRestantes = duration.minusHours(horas).toMinutes();

        Double montoFijo = tarifa.getMontoFijoAlquiler();

        Double montoHoras = horas * tarifa.getMontoHora();

        Double montoTiempoRestante = 0.0;

        if (minutosRestantes < 31) {
            montoTiempoRestante = minutosRestantes * tarifa.getMontoMinutoFraccion();
        } else {
            montoTiempoRestante = tarifa.getMontoHora();
        }

        Double montoKm = distanciaRecorrida * tarifa.getMontoKm();

        return montoFijo + montoHoras + montoTiempoRestante + montoKm;
    }
}
