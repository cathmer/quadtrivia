package com.quadsolutions.trivia.questions;

public class AnswerDTO {

    private final long questionId;
    private final String answer;

    AnswerDTO(long questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    long getQuestionId() {
        return questionId;
    }

    String getAnswer() {
        return answer;
    }
}
