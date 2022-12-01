package com.quadsolutions.trivia.questions;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final WebClient webClient;

    QuestionService(QuestionRepository questionRepository, WebClient.Builder webClientBuilder) {
        this.questionRepository = questionRepository;
        this.webClient = webClientBuilder.baseUrl("https://opentdb.com").build();
    }

    Mono<List<Question>> getQuestions(int amount) {
        return this.webClient
                .get()
                .uri("/api.php?amount={amount}", amount)
                .retrieve()
                .bodyToMono(OpenTbdDTO.class)
                .doOnNext(response -> questionRepository.saveQuestions(response.getQuestions()))
                .map(OpenTbdDTO::getQuestions);
    }
}
