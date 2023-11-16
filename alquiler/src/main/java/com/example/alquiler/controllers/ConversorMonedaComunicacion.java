package com.example.alquiler.controllers;

import com.example.alquiler.dtos.MonedaRequestDto;
import com.example.alquiler.dtos.MonedaResponseDto;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@NoArgsConstructor
public class ConversorMonedaComunicacion {
    private final String baseUrl = "http://34.82.105.125:8080";
    public MonedaResponseDto convertir(MonedaRequestDto monedaRequestDto){
        WebClient.Builder builder = WebClient.builder();
        return builder
                .baseUrl(baseUrl)
                .build()
                .post()
                .uri("/convertir")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(monedaRequestDto)
                .retrieve()
                .bodyToMono(MonedaResponseDto.class)
                .block();
    }

}
