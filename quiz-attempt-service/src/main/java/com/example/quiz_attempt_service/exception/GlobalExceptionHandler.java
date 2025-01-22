package com.example.quiz_attempt_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuizAttemptException.class)
    public ResponseEntity<ErrorDetails> handleQuizAttemptException(QuizAttemptException ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), "Quiz attempt error occurred.");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuestionAttemptException.class)
    public ResponseEntity<ErrorDetails> handleQuestionAttemptException(QuestionAttemptException ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), "Question attempt error occurred.");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuizResultException.class)
    public ResponseEntity<ErrorDetails> handleQuizResultException(QuizResultException ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), "Quiz result error occurred.");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), "An unexpected error occurred.");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
