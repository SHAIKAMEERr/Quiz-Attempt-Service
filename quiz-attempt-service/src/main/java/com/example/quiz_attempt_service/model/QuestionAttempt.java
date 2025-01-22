package com.example.quiz_attempt_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "question_attempts")
@Getter
@Setter
@NoArgsConstructor
public class QuestionAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_attempt_id")
    private Long questionAttemptId;

    @Column(name = "quiz_attempt_id")
    private Long quizAttemptId;

    @Column(name = "question_id")
    private Long questionId;  // Changed type to Long, as required

    @Column(name = "user_answer")
    private String userAnswer;

    @Column(name = "is_correct")
    private Boolean isCorrect;  // New field to track if the answer is correct

    @Column(name = "attempt_time")
    private Long attemptTime;  // Time taken to answer the question in milliseconds

    @Column(name = "correct_answer")
    private String correctAnswer;  // The correct answer for comparison
}
