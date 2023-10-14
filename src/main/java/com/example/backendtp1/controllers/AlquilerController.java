package com.example.backendtp1.controllers;

import com.example.backendtp1.entities.Estacion;
import com.example.backendtp1.entities.Tarifa;
import com.example.backendtp1.services.EstacionService;
import com.example.backendtp1.services.TarifaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/client/alquiler")
public class AlquilerController {
    private final EstacionService estacionService;
    private final TarifaService tarifaService;

    public  AlquilerController(EstacionService estacionService, TarifaService tarifaService) {
        this.estacionService = estacionService;
        this.tarifaService = tarifaService;
    }

    @GetMapping("estaciones")
    public List<Estacion> getEstaciones(){
        return estacionService.getAll();
    }


    @GetMapping("tarifas")
    public List<Tarifa> getTarifas () {
        return tarifaService.getAll();
    }
}
