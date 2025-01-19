package com.example.quiz_attempt_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuizResultException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(QuizResultException.class);

    public QuizResultException(String message) {
        super(message);
        logger.error("QuizResultException occurred: {}", message);
    }

    public QuizResultException(String message, Throwable cause) {
        super(message, cause);
        logger.error("QuizResultException occurred: {}. Cause: {}", message, cause.getMessage());
    }
}