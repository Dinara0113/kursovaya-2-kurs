package com.dinara.kursovaya_2_kurs;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private final JavaQuestionService javaQuestionService = new JavaQuestionService();

    @Test
    void addQuestionTest() {
        Question question = javaQuestionService.addQuestion("Что такое Java?", "Язык программирования");
        assertNotNull(question);
        assertEquals("Что такое Java?", question.getQuestion());
    }

}