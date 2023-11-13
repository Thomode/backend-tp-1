package com.example.backendtp1.controllers;

import com.example.backendtp1.entities.Estacion;
import com.example.backendtp1.services.EstacionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/client/estacion")
public class EstacionController {
    private final EstacionService estacionService;

    public  EstacionController(EstacionService estacionService) {
        this.estacionService = estacionService;
    }
    @GetMapping("estaciones")
    public List<Estacion> getEstaciones(){
        return estacionService.getAll();
    }
    @GetMapping("estacionMasCercana")
    public Estacion getEstacionMasCercana(@RequestParam double latitud, @RequestParam double longitud){
        return estacionService.getEstacionMasCercana(latitud, longitud);
    }
}
