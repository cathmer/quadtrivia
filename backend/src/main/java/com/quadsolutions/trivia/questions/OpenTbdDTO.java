package com.quadsolutions.trivia.questions;

import java.util.List;

public class OpenTbdDTO {

    private final int response_code;
    private final List<Question> results;

    OpenTbdDTO(int response_code, List<Question> results) {
        this.response_code = response_code;
        this.results = results;
    }

    List<Question> getQuestions() {
        return results;
    }
}
