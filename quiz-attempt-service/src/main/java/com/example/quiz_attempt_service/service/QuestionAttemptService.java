package com.example.quiz_attempt_service.service;

import com.example.quiz_attempt_service.dto.QuestionAttemptDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptRequestDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptResponseDTO;

public interface QuestionAttemptService {

    void attemptQuestion(Long attemptId, QuestionAttemptDTO questionAttemptDTO);

    QuestionAttemptDTO getQuestionAttemptById(Long attemptId, Long questionId);
    
    QuizAttemptResponseDTO attemptQuiz(QuizAttemptRequestDTO quizAttemptRequestDTO);
}
