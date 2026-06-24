package com.quizapp.quizapplication.repository;

import com.quizapp.quizapplication.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Integer> {

    List<Question> findBydifficultyLevel(String level);

    @Query(value= "SELECT * FROM question q WHERE q.category= :category AND q.difficulty_level= :difficultyLevel ORDER BY RANDOM() LIMIT :noOfQn", nativeQuery = true)
    List<Question> getRandomQuestionByTile(String category, String difficultyLevel, int noOfQn);
}
