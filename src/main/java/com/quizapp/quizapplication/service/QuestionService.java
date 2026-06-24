package com.quizapp.quizapplication.service;

import com.quizapp.quizapplication.model.Question;
import com.quizapp.quizapplication.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;
    public ResponseEntity<List<Question>> getAllQuestion() {
        try{
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK) ;
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST) ;
    }


    public List<Question> getDifficultyLevelQuestion(String level){
        return questionRepo.findBydifficultyLevel(level);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionRepo.save(question);
        return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED) ;
    }
}
