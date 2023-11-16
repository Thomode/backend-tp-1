package com.example.alquiler.services.Impl;

import com.example.alquiler.entities.Tarifa;
import com.example.alquiler.repositories.TarifaRespository;
import com.example.alquiler.services.TarifaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifaServiceImpl implements TarifaService {
    private final TarifaRespository tarifaRespository;

    public TarifaServiceImpl(TarifaRespository tarifaRespository){
        this.tarifaRespository = tarifaRespository;
    }

    @Override
    public Tarifa getById(Long id) {
        return tarifaRespository.findById(id).orElse(null);
    }

    public List<Tarifa> getAll() {
        return tarifaRespository.findAll();
    }
}
