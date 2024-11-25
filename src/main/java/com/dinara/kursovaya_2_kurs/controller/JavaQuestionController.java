package com.dinara.kursovaya_2_kurs.controller;
import com.dinara.kursovaya_2_kurs.JavaQuestionService;
import com.dinara.kursovaya_2_kurs.Question;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @PostMapping("/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return javaQuestionService.addQuestion(question, answer);
    }

    @DeleteMapping("/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        return javaQuestionService.removeQuestion(question, answer);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return javaQuestionService.getAllQuestions();
    }
}
