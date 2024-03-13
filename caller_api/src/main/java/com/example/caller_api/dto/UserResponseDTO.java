package com.example.caller_api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class UserResponseDTO {


    private String userName;
    private String questionTitle;
    private String chosenAnswer;
    private String correctAnswer;
//    private Map<String, Integer> scores; // key is the question, value is the number of times that this question has been chosen.



}
