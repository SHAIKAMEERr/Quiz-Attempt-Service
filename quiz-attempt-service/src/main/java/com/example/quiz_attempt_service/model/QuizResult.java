package com.example.quiz_attempt_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quiz_result")
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizResultId;

    @Column(name = "quiz_id", nullable = false)
    private Long quizId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "total_score", nullable = false)
    private Integer totalScore;

    @Column(name = "result_time", nullable = false)
    private Timestamp resultTime;

    @Column(name = "score")
    private Integer score;

    @Column(name = "total_questions")
    private Integer totalQuestions;

    @Column(name = "result_status", length = 50)
    private String resultStatus;
    
    private Long attemptId;
    
}
