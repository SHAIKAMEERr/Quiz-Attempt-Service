package com.example.quiz_attempt_service.service;

import com.example.quiz_attempt_service.dto.QuizResultDTO;

import java.util.Optional;

public interface QuizResultService {

    // Method to calculate and generate the quiz result
    QuizResultDTO generateQuizResult(Long quizAttemptId);

    // Method to retrieve a quiz result by its ID
    QuizResultDTO getQuizResultById(Long resultId);

    // Method to retrieve a quiz result by quiz attempt ID
    Optional<QuizResultDTO> getQuizResultByQuizAttemptId(Long quizAttemptId);

    // Method to retrieve a quiz result by user ID and quiz ID
    Optional<QuizResultDTO> getQuizResultByUserIdAndQuizId(String userId, String quizId);
}