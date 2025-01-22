package com.example.quiz_attempt_service.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quiz_attempts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_attempt_id")
    private Long quizAttemptId;
    
    // Removed the redundant 'id' field and the @GeneratedValue annotation

    @Column(name = "quiz_id")
    private Long quizId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "score")
    private Integer score;

    @Column(name = "total_questions")
    private Integer totalQuestions;

    @Column(name = "attempt_status")
    private String attemptStatus;  // Example values: "IN_PROGRESS", "COMPLETED"

    @Column(name = "percentage")
    private Double percentage;  // Add this field

    @Column(name = "result_status")
    private String resultStatus;  // Add this field

    @Column(name = "total_time_taken")
    private Double totalTimeTaken;  // Add this field

    @PrePersist
    public void prePersist() {
        if (this.startTime == null) {
            this.startTime = new Date();
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (this.endTime == null && this.attemptStatus.equals("COMPLETED")) {
            this.endTime = new Date();
        }
    }
}