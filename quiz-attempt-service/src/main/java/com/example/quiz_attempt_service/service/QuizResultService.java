package com.example.quiz_attempt_service.service;

import com.example.quiz_attempt_service.dto.QuizResultDTO;

public interface QuizResultService {

    QuizResultDTO calculateQuizResult(Long attemptId);

    QuizResultDTO getQuizResultById(Long resultId);
}
