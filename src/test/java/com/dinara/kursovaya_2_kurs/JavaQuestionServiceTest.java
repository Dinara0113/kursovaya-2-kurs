package com.dinara.kursovaya_2_kurs;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

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

    @Test
    void addQuestion() {
        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.addQuestion(null, "Ответ"));
        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.addQuestion(" ", "Ответ"));
        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.addQuestion("Вопрос", null));
    }

    @Test
    void removeQuestion() {
        javaQuestionService.addQuestion("Что такое Java?", "Язык программирования");
        Question removedQuestion = javaQuestionService.removeQuestion("Что такое Java?", "Язык программирования");
        assertNotNull(removedQuestion);
        assertEquals(0, javaQuestionService.getAllQuestions().size());
    }

    @Test
    void removeQuestion_ShouldThrowException_WhenQuestionNotFound() {
        assertThrows(IllegalArgumentException.class, () ->
                javaQuestionService.removeQuestion("Несуществующий вопрос", "Ответ"));
    }

    @Test
    void getAllQuestions_ShouldReturnAllAddedQuestions() {
        javaQuestionService.addQuestion("Что такое Java?", "Язык программирования");
        javaQuestionService.addQuestion("Что такое JDK?", "Набор инструментов для разработки на Java");

        List<Question> questions = javaQuestionService.getAllQuestions();
        assertEquals(2, questions.size());
        assertEquals("Что такое Java?", questions.get(0).getQuestion());
        assertEquals("Что такое JDK?", questions.get(1).getQuestion());
    }
    @Test
    void getRandomQuestion_ShouldReturnQuestion() {
        javaQuestionService.addQuestion("Что такое Java?", "Язык программирования");
        javaQuestionService.addQuestion("Что такое JDK?", "Набор инструментов для разработки на Java");

        Question randomQuestion = javaQuestionService.getRandomQuestion();
        assertNotNull(randomQuestion);
        assertTrue(
                randomQuestion.getQuestion().equals("Что такое Java?") ||
                        randomQuestion.getQuestion().equals("Что такое JDK?")
        );
    }
    @Test
    void getRandomQuestion_ShouldThrowException_WhenListIsEmpty() {
        assertThrows(IllegalStateException.class, javaQuestionService::getRandomQuestion);
    }


}