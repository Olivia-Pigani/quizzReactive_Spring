package com.example.quizz_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ChoiceValidConstraintValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ChoiceValid {

    String message() default "must select an answer in choices list";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};



}
