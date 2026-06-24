package com.quizapp.quizapplication.controller;

import com.quizapp.quizapplication.model.Question;
import com.quizapp.quizapplication.model.QuestionDTO;
import com.quizapp.quizapplication.model.Quiz;
import com.quizapp.quizapplication.model.Response;
import com.quizapp.quizapplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam  String difficultyLevel, @RequestParam int noOfQn) {
        return quizService.createQuiz(category, difficultyLevel, noOfQn);
    }

    @GetMapping("/get")
    public ResponseEntity<List<QuestionDTO>> getQuiz(@RequestParam String category, @RequestParam String difficultyLevel) {
         return quizService.getQuiz(category, difficultyLevel);
    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizId, @RequestBody List<Response> responses){
        return quizService.submitQuiz(quizId, responses);
    }

}

