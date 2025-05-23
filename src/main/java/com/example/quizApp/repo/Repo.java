package com.example.quizApp.repo;

import com.example.quizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  Repo extends JpaRepository<Question, Integer>{

    List<Question> findBycategory(String category);

@Query(value = "SELECT * FROM question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
