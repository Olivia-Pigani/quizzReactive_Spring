package com.example.quizz_api.controller;


import com.example.quizz_api.entity.User;
import com.example.quizz_api.service.BaseService;
import com.example.quizz_api.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
public class UserRestController {

    private final BaseService<User> userService;

    @Autowired
    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/add")
    public Mono<User> saveUserResponse(@RequestBody User user){
       return userService.save(user);
    }

    @GetMapping
    public Flux<User> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("{userId}")
    public Mono<User> getUserById(@PathVariable("userId") String userId){
        return userService.getById(userId);
    }

    @PutMapping("{userId}")
    public Mono<Void> updateUser (@PathVariable("userId")String userId,@RequestBody User user){
        return userService.update(userId,user);
    }

   @DeleteMapping("{userId}")
    public Mono<Void> deleteUserById (@PathVariable("userId")String userId){
        return userService.delete(userId);
    }





}
