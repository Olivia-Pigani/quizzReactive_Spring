package com.example.caller_api.controller;

import com.example.caller_api.dto.UserResponseDTO;
import com.example.caller_api.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("callerapi")
public class CallerRestController {
    private final QuizzService quizzService;

    @Autowired
    public CallerRestController(QuizzService quizzService) {
        this.quizzService = quizzService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserResponseDTO> get(){
        return quizzService.get();
    }



}
