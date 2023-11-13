package com.example.backendtp1.controllers;

import com.example.backendtp1.dtos.AlquilerDTO;
import com.example.backendtp1.entities.Alquiler;
import com.example.backendtp1.entities.Estacion;
import com.example.backendtp1.entities.Tarifa;
import com.example.backendtp1.services.AlquilerService;
import com.example.backendtp1.services.EstacionService;
import com.example.backendtp1.services.TarifaService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("iniciar/{idEstacionRetiro}")
    public Alquiler iniciarAlquiler(@PathVariable int idEstacionRetiro){
        return alquilerService.iniciarAlquiler(idEstacionRetiro);
    }

    @PutMapping("finalizar")
    public Alquiler finalizarAlquiler(@RequestBody AlquilerDTO alquilerDTO){
        return alquilerService.finalizarAlquiler(alquilerDTO);
    }
}
