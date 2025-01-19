package com.example.quiz_attempt_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuizAttemptException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(QuizAttemptException.class);

    public QuizAttemptException(String message) {
        super(message);
        logger.error("QuizAttemptException occurred: {}", message);
    }

    public QuizAttemptException(String message, Throwable cause) {
        super(message, cause);
        logger.error("QuizAttemptException occurred: {}. Cause: {}", message, cause.getMessage());
    }
}