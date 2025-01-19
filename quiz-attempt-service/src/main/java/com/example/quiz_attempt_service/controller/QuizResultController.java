package com.example.quiz_attempt_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_attempt_service.dto.QuizResultDTO;
import com.example.quiz_attempt_service.exception.QuizResultException;
import com.example.quiz_attempt_service.service.QuizResultService;

@RestController
@RequestMapping("/api/v1/quiz/results")
public class QuizResultController {

    private static final Logger logger = LoggerFactory.getLogger(QuizResultController.class);

    @Autowired
    private QuizResultService quizResultService;

    @PostMapping("/calculate/{attemptId}")
    public ResponseEntity<QuizResultDTO> calculateQuizResult(@PathVariable Long attemptId) {
        logger.info("Request received to calculate result for quiz attempt with attemptId: {}", attemptId);
        try {
            QuizResultDTO resultDTO = quizResultService.calculateQuizResult(attemptId);
            return new ResponseEntity<>(resultDTO, HttpStatus.OK);
        } catch (QuizResultException e) {
            logger.error("Error calculating quiz result: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error while calculating quiz result: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{resultId}")
    public ResponseEntity<QuizResultDTO> getQuizResult(@PathVariable Long resultId) {
        logger.info("Fetching quiz result with resultId: {}", resultId);
        try {
            QuizResultDTO resultDTO = quizResultService.getQuizResultById(resultId);
            return new ResponseEntity<>(resultDTO, HttpStatus.OK);
        } catch (QuizResultException e) {
            logger.error("Error fetching quiz result: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while fetching quiz result: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

