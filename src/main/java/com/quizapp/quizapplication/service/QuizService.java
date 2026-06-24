package com.quizapp.quizapplication.service;

import com.quizapp.quizapplication.model.Question;
import com.quizapp.quizapplication.model.QuestionDTO;
import com.quizapp.quizapplication.model.Quiz;
import com.quizapp.quizapplication.model.Response;
import com.quizapp.quizapplication.repository.QuestionRepo;
import com.quizapp.quizapplication.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, String difficultyLevel, int noOfQn) {

        List<Question> question= questionRepo.getRandomQuestionByTile(category,difficultyLevel, noOfQn);

        Quiz quiz= new Quiz();
        quiz.setCategory(category);
        quiz.setQuestions(question);
        quiz.setDifficultyLevel(difficultyLevel);

        quizRepo.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionDTO>> getQuiz(String category, String difficultyLevel) {
        List<Quiz> quizzes = quizRepo.findByCategoryAndDifficultyLevel(category, difficultyLevel);

        if(quizzes.isEmpty()){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }

        List<Question> questions= quizzes.get(0).getQuestions();
        List<QuestionDTO> questionsDTOList= new ArrayList<>();

        for(Question q: questions){

            QuestionDTO questionDTO = new QuestionDTO(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());

//            questionDTO.setId(q.getId());
//            questionDTO.setQuestionTitle(q.getQuestionTitle());
//            questionDTO.setOption1(q.getOption1());
//            questionDTO.setOption2(q.getOption2());
//            questionDTO.setOption3(q.getOption3());
//            questionDTO.setOption4(q.getOption4());
            questionsDTOList.add(questionDTO);
        }

        return ResponseEntity.ok(questionsDTOList);
    }

    public ResponseEntity<Integer> submitQuiz(Integer quizId, List<Response> responses) {

        Quiz quiz = quizRepo.findById(quizId).get();
        List<Question> questions = quiz.getQuestions();

        int score = 0;
        int count = 0;
        for (Response r : responses) {
            for (Question q : questions) {

                if (q.getId().equals(r.getQuestionId())) {

                    if (q.getCorrectAnswer().equals(r.getResponse())) {
                        score++;
                    }

                    break;
                }
            }
            count++;
        }
        int percentage= (score * 100) / count;

        return ResponseEntity.ok(percentage);
    }
}