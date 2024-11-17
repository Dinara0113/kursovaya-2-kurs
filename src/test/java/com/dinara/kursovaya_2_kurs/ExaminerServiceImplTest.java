package com.dinara.kursovaya_2_kurs;

import com.dinara.kursovaya_2_kurs.service.ExaminerService;
import com.dinara.kursovaya_2_kurs.service.QuestionService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ExaminerServiceImplTest {
    @Autowired
    private ExaminerService examinerService;

    @Test
    void getQuestionsShouldThrowWhenNotEnoughQuestions() {
        Exception exception = assertThrows(BadRequestException.class, () -> {
            examinerService.getQuestions(10);
        });
        assertEquals("Requested more questions than available", exception.getMessage());
    }

}