package com.example.quizz_api.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Document("user_response")
public class UserResponse {

    @Id
    private String id;

    private String userId;

    private String questionId;

    @Field("chosen_answer")
    @NotBlank
    private String chosenAnswer;



}
