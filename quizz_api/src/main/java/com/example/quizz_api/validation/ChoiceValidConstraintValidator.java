package com.example.quizz_api.validation;


        import com.example.quizz_api.entity.Question;
        import jakarta.validation.ConstraintValidator;
        import jakarta.validation.ConstraintValidatorContext;

public class ChoiceValidConstraintValidator implements ConstraintValidator<ChoiceValid, Question> {

    @Override
    public boolean isValid(Question question, ConstraintValidatorContext constraintValidatorContext) {
        int correctAnswerIndex = question.getCorrectAnswer();
        return correctAnswerIndex >= 0 && correctAnswerIndex < question.getChoices().size();    }
}
