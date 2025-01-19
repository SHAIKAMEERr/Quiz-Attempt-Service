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

import com.example.quiz_attempt_service.dto.QuestionAttemptDTO;
import com.example.quiz_attempt_service.exception.QuestionAttemptException;
import com.example.quiz_attempt_service.service.QuestionAttemptService;

@RestController
@RequestMapping("/api/v1/quiz/attempts/{attemptId}/questions")
public class QuestionAttemptController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionAttemptController.class);

    @Autowired
    private QuestionAttemptService questionAttemptService;

    @PostMapping
    public ResponseEntity<Void> attemptQuestion(@PathVariable Long attemptId, @RequestBody QuestionAttemptDTO questionAttemptDTO) {
        logger.info("Received request to attempt question with questionId: {}", questionAttemptDTO.getQuestionId());
        try {
            questionAttemptService.attemptQuestion(attemptId, questionAttemptDTO);
            logger.info("Question attempt successful for questionId: {}", questionAttemptDTO.getQuestionId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (QuestionAttemptException e) {
            logger.error("Error attempting question: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error while attempting question: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionAttemptDTO> getQuestionAttempt(@PathVariable Long attemptId, @PathVariable Long questionId) {
        logger.info("Fetching question attempt with attemptId: {} and questionId: {}", attemptId, questionId);
        try {
            QuestionAttemptDTO response = questionAttemptService.getQuestionAttemptById(attemptId, questionId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (QuestionAttemptException e) {
            logger.error("Error fetching question attempt: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while fetching question attempt: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
