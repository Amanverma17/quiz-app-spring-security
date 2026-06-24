package com.quizapp.quizapplication.controller;
import com.quizapp.quizapplication.model.Question;
import com.quizapp.quizapplication.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String greet(HttpServletRequest request){
        return request.getSession().getId();
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> getAllQuestion(){
//        System.out.println("Session ID: " + request.getSession().getId());
        return questionService.getAllQuestion();
    }

    @GetMapping("/difficulty/{level}")
    public List<Question> getDifficultyLevelQuestion(@PathVariable String level){
        return questionService.getDifficultyLevelQuestion(level);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);

    }

}
