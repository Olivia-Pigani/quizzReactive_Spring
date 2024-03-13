package com.example.caller_api.service;

import com.example.caller_api.dto.UserResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class QuizzService {

    private final WebClient webClient;


    public QuizzService() {
        this.webClient = WebClient.builder().baseUrl("http://localhost:8085").build();
    }


    public Flux<UserResponseDTO> get() {
        return webClient
                .get()
                .uri("/user-response/flux")
                .retrieve()
                .bodyToFlux(UserResponseDTO.class);
    }



}
