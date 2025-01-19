package com.example.quiz_attempt_service.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.quiz_attempt_service.exception.QuestionAttemptException;
import com.example.quiz_attempt_service.model.QuestionAttempt;

@Component
public class QuestionValidator {

    private static final Logger logger = LoggerFactory.
    		getLogger(QuestionValidator.class);

    public void validateQuestionAttempt(QuestionAttempt questionAttempt) {
        logger.info("Validating question attempt with ID: {}", 
        		questionAttempt.getQuestionAttemptId());

        if (questionAttempt.getAnswer() == null || questionAttempt.
        		getAnswer().isEmpty()) {
            logger.error("Answer cannot be null or empty for question"
            		+ " ID: {}", questionAttempt.getQuestionId());
            throw new QuestionAttemptException("Answer cannot be null or empty.");
        }

        if (questionAttempt.getQuestionId() == null || questionAttempt.getQuestionId() <= 0) {
            logger.error("Invalid question ID: {}", questionAttempt.getQuestionId());
            throw new QuestionAttemptException("Invalid question ID.");
        }

        logger.info("Question attempt validated successfully.");
    }
}
