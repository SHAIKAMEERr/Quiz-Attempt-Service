package com.example.quiz_attempt_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quiz_results")
@Getter
@Setter
@NoArgsConstructor
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_result_id")
    private Long quizResultId;
    
    @ManyToOne
    @JoinColumn(name = "user_id")  // Ensure this is the correct column for your database
    private User user;

    @Column(name = "quiz_attempt_id")
    private Long quizAttemptId;

    @Column(name = "total_score")
    private Integer totalScore;

    @Column(name = "total_questions")
    private Integer totalQuestions;

    @Column(name = "percentage")
    private Double percentage;

    @Column(name = "result_status")
    private String resultStatus;  // Example values: "PASSED", "FAILED", "IN_PROGRESS"
}
