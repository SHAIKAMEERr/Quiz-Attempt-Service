package com.example.quiz_attempt_service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz_attempt_service.dto.QuizResultDTO;
import com.example.quiz_attempt_service.exception.QuizResultException;
import com.example.quiz_attempt_service.mapper.QuizResultMapper;
import com.example.quiz_attempt_service.model.QuizResult;
import com.example.quiz_attempt_service.repository.QuizResultRepository;
import com.example.quiz_attempt_service.service.QuizResultService;

@Service
public class QuizResultServiceImpl implements QuizResultService {

    private static final Logger logger = LoggerFactory.getLogger(QuizResultServiceImpl.class);

    @Autowired
    private QuizResultRepository quizResultRepository;

    @Autowired
    private QuizResultMapper quizResultMapper;

    @Override
    public QuizResultDTO calculateQuizResult(Long attemptId) {
        try {
            logger.info("Calculating result for quiz attempt with attemptId: {}", attemptId);
            QuizResult quizResult = new QuizResult(); // Simulated result calculation logic
            quizResult.setAttemptId(attemptId);
            quizResult.setScore(85); // Example calculation
            quizResult = quizResultRepository.save(quizResult);
            return quizResultMapper.toDTO(quizResult);
        } catch (Exception e) {
            logger.error("Error occurred during quiz result calculation: {}", e.getMessage());
            throw new QuizResultException("Error during quiz result calculation", e);
        }
    }

    @Override
    public QuizResultDTO getQuizResultById(Long resultId) {
        try {
            logger.info("Fetching quiz result with resultId: {}", resultId);
            QuizResult quizResult = quizResultRepository.findById(resultId)
                .orElseThrow(() -> new QuizResultException("Quiz result not found"));
            return quizResultMapper.toDTO(quizResult);
        } catch (QuizResultException e) {
            logger.error("Error fetching quiz result: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            throw new QuizResultException("Unexpected error while fetching quiz result", e);
        }
    }

}
