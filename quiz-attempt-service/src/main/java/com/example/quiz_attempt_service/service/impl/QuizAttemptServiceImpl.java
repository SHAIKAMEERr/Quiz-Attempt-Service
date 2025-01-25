package com.example.quiz_attempt_service.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz_attempt_service.dto.QuizAttemptRequestDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptResponseDTO;
import com.example.quiz_attempt_service.mapper.QuizAttemptMapper;
import com.example.quiz_attempt_service.model.QuizAttempt;
import com.example.quiz_attempt_service.repository.QuizAttemptRepository;
import com.example.quiz_attempt_service.service.QuizAttemptService;




@Service
public class QuizAttemptServiceImpl implements QuizAttemptService {

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    @Autowired
    private QuizAttemptMapper quizAttemptMapper;

    private static final Logger logger = LoggerFactory.getLogger(
    		QuizAttemptServiceImpl.class);

    @Override
    @Transactional
    public QuizAttemptResponseDTO createQuizAttempt(QuizAttemptRequestDTO 
    		quizAttemptRequestDTO) {
    	
        logger.info("Creating new quiz attempt for userId: {}",
        		quizAttemptRequestDTO.getUserId());
        
        QuizAttempt quizAttempt = quizAttemptMapper.toEntity(quizAttemptRequestDTO);
        
        // Check for required fields
        if (quizAttempt.getQuizId() == null || quizAttempt.getUserId() == null) {
        	
            throw new IllegalArgumentException("Quiz ID and User "
            		+ "ID must not be null");
        }

        try {
            QuizAttempt savedQuizAttempt = quizAttemptRepository.save(quizAttempt);
            logger.info("Quiz attempt created successfully with ID: {}", savedQuizAttempt.getQuizAttemptId());
            return quizAttemptMapper.toDTO(savedQuizAttempt);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error saving quiz attempt: Data integrity violation", e);
            throw new RuntimeException("Quiz attempt creation failed due to data integrity violation", e);
        } catch (Exception e) {
            logger.error("Error saving quiz attempt", e);
            throw new RuntimeException("Quiz attempt creation failed due to an unknown error", e);
        }
    }

    @Override
    public Optional<QuizAttemptResponseDTO> getQuizAttemptByUserIdAndQuizId(Long userId, Long quizId) {
        return quizAttemptRepository.findByUserIdAndQuizId(userId, quizId)
                .map(quizAttemptMapper::toDTO);
    }

    @Override
    public List<QuizAttemptResponseDTO> getQuizAttemptsByUserId(Long userId) {
        List<QuizAttempt> quizAttempts = quizAttemptRepository.findByUserId(userId);
        return quizAttemptMapper.toDTOList(quizAttempts);
    }

    @Override
    public Optional<QuizAttempt> getQuizAttemptById(Long id) {
        return quizAttemptRepository.findById(id);
    }

    @Override
    public void startQuizAttempt(QuizAttempt quizAttempt) {
        logger.info("Starting quiz attempt with ID: {}", quizAttempt.getQuizAttemptId());
        // Logic for starting the quiz attempt
    }

    @Override
    public void calculateQuizResult(QuizAttempt quizAttempt) {
        logger.info("Calculating quiz result for quiz attempt with ID: {}", quizAttempt.getQuizAttemptId());
        // Logic for calculating the result
    }
    
    
}
