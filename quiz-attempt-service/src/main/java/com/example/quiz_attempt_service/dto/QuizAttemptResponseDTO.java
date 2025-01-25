package com.example.quiz_attempt_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizAttemptResponseDTO {
	
    private Long quizAttemptId;
    
    private Long quizId;
    
    private Long userId;
    
    private Date startTime;
    
    private Date endTime;
    
    private Integer score;
    
    private Integer totalQuestions;
    
    private String attemptStatus;
    
    private Double percentage;
    
    private String resultStatus;
    
    private Double totalTimeTaken;
}
