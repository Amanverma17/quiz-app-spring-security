package com.quizapp.quizapplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
    private String difficultyLevel;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Question> questions;
}
