package com.quadsolutions.trivia.questions;

public class QuestionNotFoundException extends RuntimeException {

    QuestionNotFoundException(long id) {
        super("Could not find question " + id);
    }
}
