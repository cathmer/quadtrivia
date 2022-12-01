package com.quadsolutions.trivia.questions;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class QuestionRepository {

    private final Map<Long, Question> questionsMap = new HashMap<>();

    void saveQuestions(List<Question> questions) {
        questions.forEach(this::addQuestion);
    }

    private void addQuestion(Question question) {
        questionsMap.put(question.getId(), question);
    }

    Question getQuestion(Long id) {
        return Optional.ofNullable(questionsMap.get(id)).orElseThrow(() -> new QuestionNotFoundException(id));
    }
}
