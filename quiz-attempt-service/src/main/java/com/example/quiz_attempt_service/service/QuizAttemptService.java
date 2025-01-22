package com.example.quiz_attempt_service.service;

import java.util.List;
import java.util.Optional;

import com.example.quiz_attempt_service.dto.QuizAttemptRequestDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptResponseDTO;
import com.example.quiz_attempt_service.model.QuizAttempt;

public interface QuizAttemptService {

    // Method to create a new quiz attempt
    QuizAttemptResponseDTO createQuizAttempt(QuizAttemptRequestDTO quizAttemptRequestDTO);

    // Method to get a quiz attempt by user ID and quiz ID
    Optional<QuizAttemptResponseDTO> getQuizAttemptByUserIdAndQuizId(Long userId, Long quizId);

    // Method to get all quiz attempts by user ID
    List<QuizAttemptResponseDTO> getQuizAttemptsByUserId(Long userId);

    // Method to get a quiz attempt by its ID
    Optional<QuizAttempt> getQuizAttemptById(Long id);

    // Method to start the quiz attempt (for tracking start time, etc.)
    void startQuizAttempt(QuizAttempt quizAttempt);

    // Method to calculate the quiz result (for scoring, result status, etc.)
    void calculateQuizResult(QuizAttempt quizAttempt);
}
