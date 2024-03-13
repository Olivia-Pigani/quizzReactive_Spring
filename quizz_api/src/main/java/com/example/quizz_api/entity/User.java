package com.example.quizz_api.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@Builder
@Document("user")
public class User {

    @Id
    private String id;

    @Field("user_name")
    @NotBlank
    private String userName;




}
