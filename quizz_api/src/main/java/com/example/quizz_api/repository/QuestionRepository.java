package com.example.quizz_api.repository;


import com.example.quizz_api.entity.Question;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question,String> {
}
