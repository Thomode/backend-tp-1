package com.example.alquiler.controllers;

import com.example.alquiler.dtos.EstacionResponseDto;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@NoArgsConstructor
public class EstacionServiceComunicacion {
    private final String baseUrl = "http://localhost:4001";
    WebClient.Builder webClient = WebClient.builder();
    public EstacionResponseDto getById(Long id) {
        return webClient.baseUrl(baseUrl)
                .build()
                .get()
                .uri("/api/client/estacion/"+id)
                .retrieve()
                .bodyToMono(EstacionResponseDto.class)
                .block();
    }

    public Double getDistanciaEstaciones(Long idEstacionRetiro, Long idEstacionDevolucion) {
        return webClient.baseUrl(baseUrl)
                .build()
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/api/client/estacion/distanciaEntreEstaciones")
                                .queryParam("idEstacionRetiro", idEstacionRetiro)
                                .queryParam("idEstacionDevolucion", idEstacionDevolucion)
                                .build()
                )
                .retrieve()
                .bodyToMono(Double.class)
                .block();
    }
}
