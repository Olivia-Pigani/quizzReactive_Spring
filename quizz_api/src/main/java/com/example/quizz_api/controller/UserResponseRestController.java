package com.example.quizz_api.controller;


import com.example.quizz_api.dto.UserResponseDTO;
import com.example.quizz_api.entity.UserResponse;
import com.example.quizz_api.service.BaseService;
import com.example.quizz_api.service.impl.UserResponseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping("user-response")
public class UserResponseRestController {

    private final BaseService<UserResponse> userResponseService;

    @Autowired
    public UserResponseRestController(BaseService<UserResponse> userResponseService) {
        this.userResponseService = userResponseService;
    }

    @PostMapping("/add")
    public Mono<UserResponse> saveAUserResponse(@RequestBody UserResponse userResponse){
        return userResponseService.save(userResponse);
    }




@GetMapping("users/{userId}")
    public Flux<UserResponse> getAllUserResponseByUserId(@PathVariable("userId") String userId){
        UserResponseServiceImpl castedService = (UserResponseServiceImpl) userResponseService;
        return castedService.getUserResponseByUserId(userId);
}

@GetMapping("questions/{questionId}")
    public Flux<UserResponse> getAllUserResponseByQuestionId(@PathVariable("questionId") String questionId){
        UserResponseServiceImpl castedService = (UserResponseServiceImpl) userResponseService;
        return castedService.getUserResponseByQuestionId(questionId);
}



    @GetMapping( value = "flux",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserResponseDTO> streamUserResponses() {
        UserResponseServiceImpl castedRepo = (UserResponseServiceImpl) userResponseService;
        return castedRepo.getUserResponsesStream();
    }


}
