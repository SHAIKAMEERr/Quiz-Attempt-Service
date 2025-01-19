package com.example.quiz_attempt_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder 
public class QuizResultDTO {

    private Long quizResultId; 
    
    private Long quizAttemptId;
    
    private Integer score;
    
    private Integer totalQuestions;
    
    private String resultStatus;
    
    private Long resultId;  
    
    private Long attemptId;
    
    private int totalScore; 
}
