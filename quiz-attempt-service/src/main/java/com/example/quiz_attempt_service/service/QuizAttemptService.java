package com.example.quiz_attempt_service.service;

import com.example.quiz_attempt_service.dto.QuizAttemptRequestDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptResponseDTO;
import com.example.quiz_attempt_service.model.QuizAttempt;

public interface QuizAttemptService {

    QuizAttemptResponseDTO attemptQuiz(QuizAttemptRequestDTO quizAttemptRequestDTO);

    QuizAttemptResponseDTO getQuizAttemptById(Long attemptId);

    void deleteQuizAttempt(Long attemptId);
    
    void processQuizAttempt(QuizAttempt quizAttempt);

}

