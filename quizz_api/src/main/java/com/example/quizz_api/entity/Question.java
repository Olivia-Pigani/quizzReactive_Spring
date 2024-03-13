package com.example.quizz_api.entity;

import com.example.quizz_api.validation.ChoiceValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@Builder
@Document("question")
public class Question {

    @Id
    private String id;

    @NotBlank
    private String title;

    @Size(min = 2, max = 10)
    private List<String> choices;

    @ChoiceValid // personnalized validator
    @Field("correct_answer")
    private int correctAnswer;

}
