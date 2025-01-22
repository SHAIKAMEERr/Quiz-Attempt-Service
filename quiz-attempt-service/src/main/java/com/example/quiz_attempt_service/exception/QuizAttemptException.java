package com.example.quiz_attempt_service.exception;

public class QuizAttemptException extends RuntimeException {

    public QuizAttemptException(String message) {
        super(message);
    }

    public QuizAttemptException(String message, Throwable cause) {
        super(message, cause);
    }
}
