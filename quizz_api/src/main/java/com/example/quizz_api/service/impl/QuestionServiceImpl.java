package com.example.quizz_api.service.impl;


import com.example.quizz_api.entity.Question;
import com.example.quizz_api.repository.QuestionRepository;
import com.example.quizz_api.service.BaseService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class QuestionServiceImpl implements BaseService<Question> {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Mono<Question> save(Question element) {
        return questionRepository.save(element);
    }

    @Override
    public Flux<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public Mono<Question> getById(String elementId) {
        return questionRepository.findById(elementId);
    }


    @Override
    public Mono<Void> update(String elementId, Question element) {
        return questionRepository.findById(elementId)
                .flatMap(existingQuestion -> {
                    existingQuestion.setCorrectAnswer(element.getCorrectAnswer());
                    existingQuestion.setTitle(element.getTitle());
                    existingQuestion.setChoices(element.getChoices());


                    return questionRepository.save(existingQuestion);
                })
                .then();
    }

    @Override
    public Mono<Void> delete(String elementId) {
        return questionRepository.deleteById(elementId);

    }
}
