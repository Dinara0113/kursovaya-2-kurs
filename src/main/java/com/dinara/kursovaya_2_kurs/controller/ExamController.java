package com.dinara.kursovaya_2_kurs.controller;
import com.dinara.kursovaya_2_kurs.service.ExaminerService;
import com.dinara.kursovaya_2_kurs.Question;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public List<Question> getQuestions(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }
}
