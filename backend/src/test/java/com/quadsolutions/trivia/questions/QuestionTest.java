package com.quadsolutions.trivia.questions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class QuestionTest {

    private Question createQuestion() {
        return new Question("Is this a test?", "Yes", List.of("No", "Maybe"));
    }

    @Test
    public void testGetAnswers() {
        List<String> answers = createQuestion().getAnswers();
        assertEquals(3, answers.size());
        assertTrue(answers.contains("Yes") && answers.contains("No") && answers.contains("Maybe"));

        // Code below checks that answers are stored in random order
        Set<String> firstAnswers = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            firstAnswers.add(createQuestion().getAnswers().get(0));
        }

        assertNotEquals(1, firstAnswers.size());
    }

    @Test
    public void testCheckAnswer() {
        Question question = createQuestion();
        assertFalse(question.checkAnswer("No"));
        assertFalse(question.checkAnswer("Not a valid option"));
        assertTrue(question.checkAnswer("Yes"));
    }
}
