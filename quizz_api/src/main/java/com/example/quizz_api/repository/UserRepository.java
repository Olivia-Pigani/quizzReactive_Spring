package com.example.quizz_api.repository;


import com.example.quizz_api.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends ReactiveMongoRepository<User,String> {
}
