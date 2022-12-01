package com.quadsolutions.trivia.questions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

    private final static AtomicLong counter = new AtomicLong();
    private final long id;
    private final String question;
    private final String correctAnswer;

    private final List<String> allAnswers;

    Question(String question, String correct_answer, List<String> incorrect_answers) {
        this.id = counter.incrementAndGet();
        this.question = question;
        this.correctAnswer = correct_answer;
        allAnswers = concatAnswers(correctAnswer, incorrect_answers);
    }

    private List<String> concatAnswers(String correctAnswer, List<String> incorrectAnswers) {
        ArrayList<String> answers = new ArrayList<>(incorrectAnswers);
        answers.add(correctAnswer);
        Collections.shuffle(answers);
        return answers;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return allAnswers;
    }

    boolean checkAnswer(String answer) {
        return answer.equals(correctAnswer);
    }

    @Override
    public String toString() {
        return id + ": " + question;
    }
}
