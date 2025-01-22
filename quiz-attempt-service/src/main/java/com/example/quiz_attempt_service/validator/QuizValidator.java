package com.example.quiz_attempt_service.validator;

import org.springframework.stereotype.Component;

import com.example.quiz_attempt_service.dto.QuizAttemptRequestDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class QuizValidator {

    private static final Logger logger = LoggerFactory.getLogger(QuizValidator.class);

    // Validate if the QuizAttemptRequestDTO contains valid data
    public boolean validateQuizAttemptRequest(QuizAttemptRequestDTO quizAttemptRequestDTO) {
        if (quizAttemptRequestDTO == null) {
            logger.warn("Quiz attempt request DTO is null.");
            return false;
        }
        
        if (quizAttemptRequestDTO.getQuizId() == null || quizAttemptRequestDTO.getQuizId()<=0) {
            logger.warn("Quiz ID is missing in the request.");
            return false;
        }
        
        if (quizAttemptRequestDTO.getUserId() == null || quizAttemptRequestDTO.getUserId()<=0) {
            logger.warn("User ID is missing in the request.");
            return false;
        }

        // Additional validation checks can be added as required
        logger.info("Quiz attempt request validation passed.");
        return true;
    }
}
