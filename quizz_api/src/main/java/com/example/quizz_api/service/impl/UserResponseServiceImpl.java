package com.example.quizz_api.service.impl;


import com.example.quizz_api.dto.UserResponseDTO;
import com.example.quizz_api.entity.Question;
import com.example.quizz_api.entity.User;
import com.example.quizz_api.entity.UserResponse;
import com.example.quizz_api.repository.QuestionRepository;
import com.example.quizz_api.repository.UserRepository;
import com.example.quizz_api.repository.UserResponseRepository;
import com.example.quizz_api.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
public class UserResponseServiceImpl implements BaseService<UserResponse> {


    private final UserResponseRepository userResponseRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final Sinks.Many<UserResponseDTO> userResponseDTOSink;

    @Autowired
    public UserResponseServiceImpl(UserResponseRepository userResponseRepository, QuestionRepository questionRepository, UserRepository userRepository) {
        this.userResponseRepository = userResponseRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.userResponseDTOSink = Sinks.many().multicast().onBackpressureBuffer();
    }


    @Override
    public Mono<UserResponse> save(UserResponse element) {
        Mono<User> userMono = userRepository.findById(element.getUserId());
        Mono<Question> questionMono = questionRepository.findById(element.getQuestionId());


        return Mono.zip(userMono,questionMono,Mono.just(element))
                .flatMap(tuple -> {
                    User user = tuple.getT1();
                    Question question = tuple.getT2();
                    UserResponse userResponse = tuple.getT3();

                    UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                            .userName(user.getUserName())
                            .questionTitle(question.getTitle())
                            .chosenAnswer(userResponse.getChosenAnswer().toLowerCase())
                            .correctAnswer(question.getChoices().get(question.getCorrectAnswer()))
                            .build();

                    userResponseDTOSink.tryEmitNext(userResponseDTO)
                          ;
                    System.out.println("UserResponseDTO published in the Sink " + userResponseDTO);


                    return userResponseRepository.save(userResponse)
                            .doOnSuccess(saved -> System.out.println("UserResponse published with success: " + saved))
                            .doOnError(error -> System.out.println("Error while attempting to published in the Sink: " + error.getMessage()));


                });

    }

    @Override
    public Flux<UserResponse> getAll() {
        return userResponseRepository.findAll();
    }

    @Override
    public Mono<UserResponse> getById(String elementId) {
        return userResponseRepository.findById(elementId);
    }

    @Override
    public Mono<Void> update(String elementId, UserResponse element) {
        return userResponseRepository.findById(elementId)
                .flatMap(existingUserResponse -> {
                    existingUserResponse.setChosenAnswer(element.getChosenAnswer());

                    return userResponseRepository.save(existingUserResponse);
                })
                .then();
    }


    @Override
    public Mono<Void> delete(String elementId) {
        return userResponseRepository.deleteById(elementId);

    }


    // SPECIFIC METHODS

    public Flux<UserResponse> getUserResponseByUserId(String userId) {
        return userResponseRepository.getUserResponseByUserId(userId);
    }

    public Flux<UserResponse> getUserResponseByQuestionId(String questionId) {
        return userResponseRepository.getUserResponseByQuestionId(questionId);
    }


    public Flux<UserResponseDTO> getUserResponsesStream() {
        return this.userResponseDTOSink.asFlux();
    }



}
