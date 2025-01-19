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
@Table(name = "question_attempt")
public class QuestionAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionAttemptId;  // Unique ID for each question attempt

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_attempt_id", nullable = false)
    private QuizAttempt quizAttempt;  // Links to QuizAttempt, ensuring every question belongs to a specific attempt

    @Column(name = "question_id", nullable = false)
    private Long questionId;  // Unique identifier for the question being attempted

    @Column(name = "answer", length = 500)
    private String answer;  // Stores the answer provided by the user

    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect;  // Indicates if the answer was correct

    @Column(name = "attempt_time", nullable = false, updatable = false)
    private Timestamp attemptTime;  // Timestamp when the question was attempted
}
