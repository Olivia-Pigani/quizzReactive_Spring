package com.example.quizz_api.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseService<T> {

    public Mono<T> save(T element);

    public Flux<T> getAll();

    public Mono<T> getById(String elementId);

    public Mono<Void> update(String elementId, T element);

    public Mono<Void> delete(String elementId);

}
