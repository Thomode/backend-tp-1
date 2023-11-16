package com.example.estacion.controllers;

import com.example.estacion.dtos.EstacionDTO;
import com.example.estacion.dtos.EstacionDistanciaDTO;
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
    public EstacionDistanciaDTO calcular(@RequestParam Long idEstacionRetiro, @RequestParam Long idEstacionDevolucion){
        EstacionDistanciaDTO estacionDistanciaDTO = new EstacionDistanciaDTO();
        estacionDistanciaDTO.setDistancia(this.estacionService.getDistanciaEstaciones(idEstacionRetiro,idEstacionDevolucion));

        return estacionDistanciaDTO;
    }

    @PostMapping
    public Estacion add(@RequestBody EstacionDTO estacionDTO){
        return estacionService.save(estacionDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Estacion> getById(@PathVariable Long idEstacion){
        Estacion estacion = estacionService.getById(idEstacion);

        if(estacion == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estacion);
    }
}
