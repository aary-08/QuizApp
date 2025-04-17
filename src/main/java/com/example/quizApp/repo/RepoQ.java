package com.example.quizApp.repo;



import com.example.quizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  RepoQ extends JpaRepository<Quiz, Integer> {


}

