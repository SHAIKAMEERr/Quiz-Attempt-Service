package com.example.quiz_attempt_service.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.quiz_attempt_service.exception.QuizAttemptException;
import com.example.quiz_attempt_service.model.QuizAttempt;

@Component
public class QuizValidator {

    private static final Logger logger = LoggerFactory.getLogger(QuizValidator.class);

    public void validateQuizAttempt(QuizAttempt quizAttempt) {
    	
        logger.info("Validating quiz attempt with ID: {}", quizAttempt.getQuizAttemptId());
        
        if (quizAttempt.getQuestions() == null || quizAttempt.getQuestions().isEmpty()) {
        	
            logger.error("Quiz attempt must contain at least one question.");
            
            throw new QuizAttemptException("Quiz attempt must contain at least one question.");
        }
        if (quizAttempt.getUser() == null) {
        	
            logger.error("Quiz attempt must be associated with a valid user.");
            
            throw new QuizAttemptException("Quiz attempt must be associated with a valid user.");
        }
        logger.info("Quiz attempt validated successfully.");
    }
}
