package com.quadsolutions.trivia.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions")
    Mono<List<Question>> questions(@RequestParam(defaultValue = "5") int amount) {
        return questionService.getQuestions(amount);
    }

    @PostMapping("/checkanswers")
    boolean answerIsCorrect(@RequestBody AnswerDTO answer) {
        return questionRepository.getQuestion(answer.getQuestionId()).checkAnswer(answer.getAnswer());
    }
}
