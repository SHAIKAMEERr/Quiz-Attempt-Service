package com.example.quiz_attempt_service.service;

import java.util.List;
import java.util.Optional;

import com.example.quiz_attempt_service.dto.QuestionAttemptDTO;
import com.example.quiz_attempt_service.model.QuestionAttempt;

public interface QuestionAttemptService {

    QuestionAttemptDTO recordQuestionAttempt(Long quizAttemptId, Long questionId, String userAnswer);

    Optional<QuestionAttemptDTO> getQuestionAttempt(Long quizAttemptId, Long questionId);

    List<QuestionAttempt> getQuestionAttemptsByQuizAttemptId(Long quizAttemptId);
    
}
