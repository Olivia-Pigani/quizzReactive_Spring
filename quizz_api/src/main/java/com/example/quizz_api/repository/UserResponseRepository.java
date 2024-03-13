package com.example.quizz_api.repository;


import com.example.quizz_api.dto.UserResponseDTO;
import com.example.quizz_api.entity.UserResponse;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserResponseRepository extends ReactiveMongoRepository<UserResponse,String> {


    public Flux<UserResponse> getUserResponseByUserId(String userId);

    public Flux<UserResponse> getUserResponseByQuestionId(String questionId);


}
