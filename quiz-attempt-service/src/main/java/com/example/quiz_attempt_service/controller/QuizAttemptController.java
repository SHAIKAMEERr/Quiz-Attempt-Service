package com.example.quiz_attempt_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_attempt_service.dto.QuizAttemptRequestDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptResponseDTO;
import com.example.quiz_attempt_service.exception.QuizAttemptException;
import com.example.quiz_attempt_service.service.QuizAttemptService;

@RestController
@RequestMapping("/api/v1/quiz/attempts")
public class QuizAttemptController {

    private static final Logger logger = LoggerFactory.getLogger(QuizAttemptController.class);

    @Autowired
    private QuizAttemptService quizAttemptService;

    @PostMapping
    public ResponseEntity<QuizAttemptResponseDTO> attemptQuiz(@RequestBody QuizAttemptRequestDTO quizAttemptRequestDTO) {
        logger.info("Request received to attempt quiz with quizId: {}", quizAttemptRequestDTO.getQuizId());
        try {
            QuizAttemptResponseDTO response = quizAttemptService.attemptQuiz(quizAttemptRequestDTO);
            logger.info("Quiz attempt created successfully with quizId: {}", quizAttemptRequestDTO.getQuizId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (QuizAttemptException e) {
            logger.error("Error attempting quiz: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error while attempting quiz: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{attemptId}")
    public ResponseEntity<QuizAttemptResponseDTO> getQuizAttempt(@PathVariable Long attemptId) {
        logger.info("Request received to get quiz attempt with attemptId: {}", attemptId);
        try {
            QuizAttemptResponseDTO response = quizAttemptService.getQuizAttemptById(attemptId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (QuizAttemptException e) {
            logger.error("Error fetching quiz attempt: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while fetching quiz attempt: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

