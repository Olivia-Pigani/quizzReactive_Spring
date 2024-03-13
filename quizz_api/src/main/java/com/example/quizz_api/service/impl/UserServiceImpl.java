package com.example.quizz_api.service.impl;


import com.example.quizz_api.entity.User;
import com.example.quizz_api.repository.UserRepository;
import com.example.quizz_api.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements BaseService<User> {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public Mono<User> save(User element) {
        return userRepository.save(element);
    }

    @Override
    public Flux<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> getById(String elementId) {
        return userRepository.findById(elementId);
    }



    @Override
    public Mono<Void> update(String elementId, User element) {
        return userRepository.findById(elementId)
                .flatMap(existingUser -> {
                    existingUser.setUserName(element.getUserName());

                    return userRepository.save(existingUser);
                })
                .then();
    }



    @Override
    public Mono<Void> delete(String elementId) {
        return userRepository.deleteById(elementId);

    }





}
