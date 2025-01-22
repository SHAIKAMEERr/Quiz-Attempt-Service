package com.example.quiz_attempt_service.controller;

import java.util.Optional;

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
import com.example.quiz_attempt_service.dto.QuizAttemptRequestDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptResponseDTO;
import com.example.quiz_attempt_service.mapper.QuizAttemptMapper;
import com.example.quiz_attempt_service.service.QuestionAttemptService;
import com.example.quiz_attempt_service.service.QuizAttemptService;

@RestController
@RequestMapping("/api/quiz-attempts")
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private QuizAttemptService quizAttemptService;

    @Autowired
    private QuestionAttemptService questionAttemptService;
    
    @Autowired
    private QuizAttemptMapper quizAttemptMapper;

    // Create a new quiz attempt
    @PostMapping
    public ResponseEntity<QuizAttemptResponseDTO> createQuizAttempt(
    		@RequestBody QuizAttemptRequestDTO quizAttemptRequestDTO) {
    	
        logger.info("Creating new quiz attempt for userId: {}, quizId: {}", 
        		quizAttemptRequestDTO.getUserId(), quizAttemptRequestDTO.getQuizId());
        
        QuizAttemptResponseDTO response = quizAttemptService.createQuizAttempt(
        		quizAttemptRequestDTO);
        
        logger.info("Created quiz attempt with ID: {}", response.getQuizId());
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/quiz/{quizId}")
    public ResponseEntity<QuizAttemptResponseDTO> getQuizAttemptByUserAndQuiz(
    		@PathVariable String userId, @PathVariable String quizId) {
    	
        logger.info("Fetching quiz attempt for userId: {}, quizId: {}", userId, quizId);

        try {
            Long userIdLong = Long.parseLong(userId); // Convert userId to Long
            Long quizIdLong = Long.parseLong(quizId); // Convert quizId to Long
            
            logger.debug("Converted userId to Long: {}, quizId to Long:"
            		+ " {}", userIdLong, quizIdLong);

            Optional<QuizAttemptResponseDTO> quizAttempt = quizAttemptService
            		.getQuizAttemptByUserIdAndQuizId(userIdLong, quizIdLong);

            if (quizAttempt.isPresent()) {
            	
                logger.info("Found quiz attempt for userId: {} and quizId: {}"
                		, userId, quizId);
                
                return ResponseEntity.ok(quizAttempt.get());
            } else {
            	
                logger.warn("No quiz attempt found for userId: {} and quizId:"
                		+ " {}", userId, quizId);
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (NumberFormatException e) {
        	
            logger.error("Invalid userId or quizId format: userId={}, "
            		+ "quizId={}", userId, quizId, e);
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{quizAttemptId}/{questionId}")
    public ResponseEntity<QuestionAttemptDTO> getQuestionAttempt(
            @PathVariable Long quizAttemptId,
            @PathVariable Long questionId) {

        logger.info("Fetching question attempt for quizAttemptId: "
        		+ "{}, questionId: {}", quizAttemptId, questionId);

        // Call the service to fetch the QuestionAttemptDTO
        Optional<QuestionAttemptDTO> questionAttemptOptional = 
        		questionAttemptService.getQuestionAttempt(quizAttemptId, questionId);
        
        // Check if the Optional contains a value and return the corresponding ResponseEntity
        return questionAttemptOptional
                .map(questionAttempt -> {
                    logger.info("Found question attempt for quizAttemptId: {},"
                    		+ " questionId: {}", quizAttemptId, questionId);
                    
                    return ResponseEntity.ok(questionAttempt); // If present, return 200 OK
                })
                .orElseGet(() -> {
                    logger.warn("No question attempt found for quizAttemptId: {}, questionId: {}", quizAttemptId, questionId);
                    return ResponseEntity.status(404).build(); // If not, return 404 Not Found
                });
    }

}
