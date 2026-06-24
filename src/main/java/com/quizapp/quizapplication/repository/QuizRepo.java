package com.quizapp.quizapplication.repository;

import com.quizapp.quizapplication.model.Question;
import com.quizapp.quizapplication.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {


    List<Quiz> findByCategoryAndDifficultyLevel(String category, String difficultyLevel);
}
