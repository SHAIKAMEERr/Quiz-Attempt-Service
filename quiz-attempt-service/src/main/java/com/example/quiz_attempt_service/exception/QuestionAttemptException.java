package com.example.quiz_attempt_service.exception;

public class QuestionAttemptException extends RuntimeException {

    public QuestionAttemptException(String message) {
        super(message);
    }

    public QuestionAttemptException(String message, Throwable cause) {
        super(message, cause);
    }
}