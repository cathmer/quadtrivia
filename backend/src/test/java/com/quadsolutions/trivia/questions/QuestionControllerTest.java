package com.quadsolutions.trivia.questions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.BDDMockito.given;

@WebFluxTest(QuestionController.class)
@Import(QuestionRepository.class)
public class QuestionControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    QuestionService questionService;

    @MockBean
    QuestionRepository questionRepository;

    @Test
    void testGetQuestions() {
        List<Question> questions = List.of(new Question("Is this a test?", "Yes", List.of("No")));
        given(questionService.getQuestions(1)).willReturn(Mono.just(questions));

        webClient.get().uri("/questions?amount=1")
                .exchange()
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo(1)
                .jsonPath("$.[0].question").isEqualTo("Is this a test?")
                .jsonPath("$.[0].answers").isArray();
    }

    @Test
    void testCheckAnswer() {
        Question question = new Question("Is this a test?", "Yes", List.of("No"));
        given(questionRepository.getQuestion(1L)).willReturn(question);

        webClient.post()
                .uri("/checkanswers")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"questionId\": 1, \"answer\": \"Yes\"}")
                .exchange()
                .expectBody(Boolean.class)
                .isEqualTo(true);

        webClient.post()
                .uri("/checkanswers")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"questionId\": 1, \"answer\": \"No\"}")
                .exchange()
                .expectBody(Boolean.class)
                .isEqualTo(false);
    }
}
