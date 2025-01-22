package com.example.quiz_attempt_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAttemptDTO {

    private Long questionAttemptId;
    
    private Long quizAttemptId;
    
    private Long questionId;  // Retaining Long type as per the updated entity
    
    private String userAnswer;
    
    private Boolean isCorrect;  // Added to reflect whether the answer is correct
    
    private Long attemptTime;  // Added to store time taken to answer the question
}
