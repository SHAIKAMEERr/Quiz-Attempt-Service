package com.example.quiz_attempt_service.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz_attempt_service.dto.QuestionAttemptDTO;
import com.example.quiz_attempt_service.mapper.QuestionAttemptMapper;
import com.example.quiz_attempt_service.model.QuestionAttempt;
import com.example.quiz_attempt_service.repository.QuestionAttemptRepository;
import com.example.quiz_attempt_service.service.QuestionAttemptService;


@Service
public class QuestionAttemptServiceImpl implements QuestionAttemptService {

    private static final Logger logger = LoggerFactory.getLogger(
    		QuestionAttemptServiceImpl.class);

    @Autowired
    private QuestionAttemptRepository questionAttemptRepository;

    @Autowired
    private QuestionAttemptMapper questionAttemptMapper;

    @Override
    public QuestionAttemptDTO recordQuestionAttempt(Long quizAttemptId, 
    		Long questionId, String userAnswer) {
    	
        logger.info("Recording question attempt for quizAttemptId: {}, "
        		+ "questionId: {}, userAnswer: {}", quizAttemptId, questionId, userAnswer);

        // Create and populate the QuestionAttempt entity
        QuestionAttempt questionAttempt = new QuestionAttempt();
        questionAttempt.setQuizAttemptId(quizAttemptId);

        // Convert the String questionId to Long before setting it in the entity
        try {
            Long questionIdLong = questionId;
            questionAttempt.setQuestionId(questionIdLong);
            
            logger.debug("Converted questionId {} to Long: {}",
            		questionId, questionIdLong);
            
        } catch (NumberFormatException e) {
        	
            logger.error("Invalid questionId: {}. It must be numeric.", 
            		questionId, e);
            
            throw new IllegalArgumentException("Invalid questionId. It"
            		+ " must be a numeric value.", e);
        }

        questionAttempt.setUserAnswer(userAnswer);

        // Save the question attempt
        QuestionAttempt savedQuestionAttempt = questionAttemptRepository
        		.save(questionAttempt);
        
        logger.info("Saved question attempt with ID: {}", 
        		savedQuestionAttempt.getQuestionId());

        // Map the entity to DTO
        QuestionAttemptDTO response = questionAttemptMapper.toDTO(savedQuestionAttempt);
        
        logger.debug("Mapped saved question attempt to DTO: {}", response);

        return response;
    }


    @Override
    public Optional<QuestionAttemptDTO> getQuestionAttempt(
    		Long quizAttemptId, Long questionId) {

    	logger.info("Attempting to fetch question attempt for quizAttemptId:"
    			+ " {}, questionId: {}", quizAttemptId, questionId);

        // Fetch the question attempt from the repository
        Optional<QuestionAttempt> questionAttemptOpt = questionAttemptRepository
        		.findByQuizAttemptIdAndQuestionId(quizAttemptId, questionId);

        if (questionAttemptOpt.isEmpty()) {

        	logger.warn("No question attempt found for quizAttemptId: "
            		+ "{} and questionId: {}", quizAttemptId, questionId);
            
            return Optional.empty(); // Return empty Optional
        }

        logger.info("Found question attempt for quizAttemptId: {}, "
        		+ "questionId: {}", quizAttemptId, questionId);

        // Map the entity to DTO
        QuestionAttemptDTO response = questionAttemptMapper.toDTO(questionAttemptOpt.get());

        // Log the mapped DTO
        logger.debug("Mapped question attempt entity to DTO: {}", response);

        return Optional.of(response); // Return the DTO wrapped in an Optional
    }

    @Override
    public List<QuestionAttempt> getQuestionAttemptsByQuizAttemptId(Long quizAttemptId) {
    	
        logger.info("Fetching question attempts for quizAttemptId: {}", quizAttemptId);
        
        return questionAttemptRepository.findByQuizAttemptId(quizAttemptId);
    }
}
