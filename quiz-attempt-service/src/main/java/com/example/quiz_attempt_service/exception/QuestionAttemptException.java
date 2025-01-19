package com.example.quiz_attempt_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuestionAttemptException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(QuestionAttemptException.class);

    public QuestionAttemptException(String message) {
        super(message);
        logger.error("QuestionAttemptException occurred: {}", message);
    }

    public QuestionAttemptException(String message, Throwable cause) {
        super(message, cause);
        logger.error("QuestionAttemptException occurred: {}. Cause: {}", message, cause.getMessage());
    }
}
