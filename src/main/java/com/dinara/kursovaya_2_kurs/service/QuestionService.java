package com.dinara.kursovaya_2_kurs.service;

import com.dinara.kursovaya_2_kurs.Question;

import java.util.List;

public interface QuestionService {
    Question addQuestion(String question, String answer);
    Question removeQuestion(String question, String answer);
    List<Question> getAllQuestions();
    Question getRandomQuestion();
}
