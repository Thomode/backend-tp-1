package com.example.estacion.controllers;

import com.example.estacion.dtos.EstacionDTO;
import com.example.estacion.entities.Estacion;
import com.example.estacion.services.EstacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return this.estacionService.getEstacionMasCercana(latitud, longitud);
    }

    @GetMapping("distanciaEntreEstaciones")
    public Double distanciaEntreEstaciones(@RequestParam Long idEstacionRetiro, @RequestParam Long idEstacionDevolucion){
        return this.estacionService.getDistanciaEstaciones(idEstacionRetiro,idEstacionDevolucion);
    }

    @PostMapping
    public ResponseEntity<Estacion> add(@RequestBody EstacionDTO estacionDTO){
        Estacion estacion = estacionService.save(estacionDTO);

        if(estacion == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estacion);
    }

    @GetMapping("{idEstacion}")
    public ResponseEntity<Estacion> getById(@PathVariable Long idEstacion){
        Estacion estacion = estacionService.getById(idEstacion);

        if(estacion == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estacion);
    }
}
