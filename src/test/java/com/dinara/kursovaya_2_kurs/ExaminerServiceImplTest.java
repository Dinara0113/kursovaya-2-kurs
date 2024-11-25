package com.dinara.kursovaya_2_kurs;

import com.dinara.kursovaya_2_kurs.service.ExaminerService;
import com.dinara.kursovaya_2_kurs.service.QuestionService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {
    @Autowired
    private ExaminerService examinerService;
    private QuestionService questionServiceMock;

    @BeforeEach
    void setUp() {
        questionServiceMock = mock(QuestionService.class); // Создаем мок для QuestionService
        examinerService = new ExaminerServiceImpl(questionServiceMock); // Передаем мок в ExaminerServiceImpl
    }

    @Test
    void getQuestions_ShouldReturnRequestedAmount() {
        //data
        List<Question> mockQuestions = List.of(
                new Question("Что такое Java?", "Язык программирования"),
                new Question("Что такое JDK?", "Набор инструментов для Java")
        );
        when(questionServiceMock.getAllQuestions()).thenReturn(mockQuestions);
        when(questionServiceMock.getRandomQuestion())
                .thenReturn(mockQuestions.get(0))
                .thenReturn(mockQuestions.get(1)); // Возвращаем вопросы по очереди

        // Вызываю метод
        List<Question> result = examinerService.getQuestions(2);

        // Проверки
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(mockQuestions.get(0)));
        assertTrue(result.contains(mockQuestions.get(1)));

        // Убедимся, что метод getRandomQuestion вызывался дважды
        verify(questionServiceMock, times(2)).getRandomQuestion();
    }

    @Test
    void getQuestions_ShouldThrowException_WhenRequestedAmountExceedsAvailable() {
        when(questionServiceMock.getAllQuestions()).thenReturn(new ArrayList<>());

        // Вызываю метод и проверяю исключение
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            examinerService.getQuestions(5);
        });

        // check
        assertEquals("400 BAD_REQUEST \"Запрошено больше вопросов, чем доступно.\"", exception.getMessage());
    }


    @Test
    void getQuestions_ShouldReturnUniqueQuestions() {
        // data
        Question q1 = new Question("Что такое Java?", "Язык программирования");
        Question q2 = new Question("Что такое JDK?", "Набор инструментов для Java");
        List<Question> mockQuestions = List.of(q1, q2);

        when(questionServiceMock.getAllQuestions()).thenReturn(mockQuestions);
        when(questionServiceMock.getRandomQuestion())
                .thenReturn(q1)
                .thenReturn(q1) // Повторяется, чтобы проверить уникальность
                .thenReturn(q2);

        // Вызываю метод
        List<Question> result = examinerService.getQuestions(2);

        // Проверки
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(q1));
        assertTrue(result.contains(q2));
        verify(questionServiceMock, atLeast(2)).getRandomQuestion(); // Убедимся, что метод вызывался несколько раз
    }

    @Test
    void getQuestionsShouldThrowWhenNotEnoughQuestions() {
        Exception exception = assertThrows(BadRequestException.class, () -> {
            examinerService.getQuestions(10);
        });
        assertEquals("Requested more questions than available", exception.getMessage());
    }

}