package com.example.alquiler.controllers;

import com.example.alquiler.dtos.AlquilerDTO;
import com.example.alquiler.dtos.AlquilerFiltroDTO;
import com.example.alquiler.dtos.AlquilerFinDTO;
import com.example.alquiler.dtos.AlquilerInicioDTO;
import com.example.alquiler.entities.Alquiler;
import com.example.alquiler.services.AlquilerService;
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
    public Alquiler iniciarAlquiler(@RequestBody AlquilerInicioDTO alquilerInicioDTO){
        return alquilerService.iniciarAlquiler(alquilerInicioDTO);
    }

    @PutMapping("finalizar")
    public AlquilerDTO finalizarAlquiler(@RequestBody AlquilerFinDTO alquilerFinDTO){
        return alquilerService.finalizarAlquiler(alquilerFinDTO);
    }

    @GetMapping("realizados")
    public List<Alquiler> getAlquileresFinalizados(@RequestParam double montoInicio, @RequestParam double montoFin){
        return alquilerService.getAlquileresFinalizados(montoInicio, montoFin);
    }
}
