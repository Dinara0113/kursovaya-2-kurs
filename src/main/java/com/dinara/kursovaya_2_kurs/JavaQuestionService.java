package com.dinara.kursovaya_2_kurs;
import com.dinara.kursovaya_2_kurs.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class JavaQuestionService implements QuestionService {
    private final List<Question> questions = new ArrayList<>();
    private final Random random = new Random();

    @Override
    public Question addQuestion(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question removeQuestion(String question, String answer) {
        Question questionToRemove = new Question(question, answer);
        questions.remove(questionToRemove);
        return questionToRemove;
    }

    @Override
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        int randomIndex = random.nextInt(questions.size());
        return questions.get(randomIndex);
    }
}
