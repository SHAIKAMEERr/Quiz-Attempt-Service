package com.example.quiz_attempt_service.exception;

public class QuizResultException extends RuntimeException {

    public QuizResultException(String message) {
        super(message);
    }

    public QuizResultException(String message, Throwable cause) {
        super(message, cause);
    }
}
