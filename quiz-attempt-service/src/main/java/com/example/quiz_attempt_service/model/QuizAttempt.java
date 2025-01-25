package com.example.quiz_attempt_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "quiz_attempts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @PrePersist
    public void onPrePersist() {
        this.startTime = new Date();
        this.attemptStatus = "IN_PROGRESS";
    }

    @PreUpdate
    public void onPreUpdate() {
        if (this.attemptStatus.equals("COMPLETED") && this.endTime == null) {
            this.endTime = new Date();
        }
    }
}
