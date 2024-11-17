package com.dinara.kursovaya_2_kurs.service;

import com.dinara.kursovaya_2_kurs.Question;

import java.util.List;

public interface ExaminerService {
    List<Question> getQuestions(int amount);
}
