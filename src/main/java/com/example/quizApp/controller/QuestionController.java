package com.example.quizApp.controller;

import com.example.quizApp.model.Question;
import com.example.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ClientInfoStatus;
import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService qs;
    @GetMapping("all")
    public ResponseEntity<List<Question>> addAllQuest()
    {
        return qs.getquestion();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory (@PathVariable String category)
    {
        return qs.getQuestionByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
         return qs.addQuestion(question);
    }




}
