package com.example.quizz_api.controller;


import com.example.quizz_api.entity.Question;
import com.example.quizz_api.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("questions")
public class QuestionRestController {

  private final BaseService<Question> questionService;


    public QuestionRestController(BaseService<Question> questionService) {
        this.questionService = questionService;
    }

    @PostMapping("add")
    public ResponseEntity<Mono<Question>> saveAQuestion(@RequestBody Question question){
       Mono<Question> newQuestion =  questionService.save(question);
       return ResponseEntity.ok(newQuestion);
    }

    @GetMapping
    public Flux<Question> getAllQuestions(){
       return questionService.getAll();
    }

    @GetMapping("{questionId}")
    public Mono<Question> getQuestionById(@PathVariable("questionId") String questionId){
        return questionService.getById(questionId);
    }

    @PutMapping("/{questionId}")
    public Mono<Void> updateQuestion(@PathVariable String questionId, @RequestBody Question updatedQuestion){
        return questionService.update(questionId,updatedQuestion);
    }

    @DeleteMapping ("{questionId}")
    public Mono<Void> deleteQuestionById (@PathVariable("questionId")String questionId){
        return questionService.delete(questionId);
    }


}
