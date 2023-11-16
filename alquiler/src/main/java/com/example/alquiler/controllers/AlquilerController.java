package com.example.alquiler.controllers;

import com.example.alquiler.dtos.AlquilerDTO;
import com.example.alquiler.dtos.AlquilerFiltroDTO;
import com.example.alquiler.dtos.AlquilerFinDTO;
import com.example.alquiler.dtos.AlquilerInicioDTO;
import com.example.alquiler.entities.Alquiler;
import com.example.alquiler.services.AlquilerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/client/alquiler")
public class AlquilerController {
    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }
    @GetMapping
    public List<Alquiler> getAlquileres(){
        return alquilerService.getAll();
    }

    @PostMapping("iniciar")
    public ResponseEntity<Alquiler> iniciarAlquiler(@RequestBody AlquilerInicioDTO alquilerInicioDTO){
        Alquiler alquiler = alquilerService.iniciarAlquiler(alquilerInicioDTO);

        if(alquiler == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alquiler);
    }

    @PutMapping("finalizar")
    public ResponseEntity<AlquilerDTO> finalizarAlquiler(@RequestBody AlquilerFinDTO alquilerFinDTO){
        AlquilerDTO alquiler = alquilerService.finalizarAlquiler(alquilerFinDTO);

        if(alquiler == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alquiler);
    }

    @GetMapping("realizados")
    public List<Alquiler> getAlquileresFinalizados(@RequestParam double montoInicio, @RequestParam double montoFin){
        return alquilerService.getAlquileresFinalizados(montoInicio, montoFin);
    }
}
