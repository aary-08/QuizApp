package com.example.quizApp.service;

import com.example.quizApp.model.Question;
import com.example.quizApp.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    Repo repo1;
    public ResponseEntity<List<Question>> getquestion() {
         return new ResponseEntity<>(repo1.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        return new ResponseEntity<>(repo1.findBycategory(category),HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        repo1.save(question);
        return new ResponseEntity<>( "Success",HttpStatus.CREATED);
    }
}
