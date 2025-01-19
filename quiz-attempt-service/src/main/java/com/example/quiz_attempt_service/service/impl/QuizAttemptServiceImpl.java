package com.example.quiz_attempt_service.service.impl;

import com.example.quiz_attempt_service.dto.QuizAttemptRequestDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptResponseDTO;
import com.example.quiz_attempt_service.mapper.QuizAttemptMapper;
import com.example.quiz_attempt_service.model.QuizAttempt;
import com.example.quiz_attempt_service.repository.QuizAttemptRepository;
import com.example.quiz_attempt_service.service.QuizAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class QuizAttemptServiceImpl implements QuizAttemptService {

    private static final Logger logger = LoggerFactory.getLogger(QuizAttemptServiceImpl.class);

    private final QuizAttemptRepository quizAttemptRepository;
    private final QuizAttemptMapper quizAttemptMapper;
    private final ApplicationEventPublisher eventPublisher;

    public QuizAttemptServiceImpl(QuizAttemptRepository quizAttemptRepository,
                                   QuizAttemptMapper quizAttemptMapper,
                                   ApplicationEventPublisher eventPublisher) {
        this.quizAttemptRepository = quizAttemptRepository;
        this.quizAttemptMapper = quizAttemptMapper;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public QuizAttemptResponseDTO attemptQuiz(QuizAttemptRequestDTO quizAttemptRequestDTO) {
        logger.info("Starting quiz attempt with request: {}", quizAttemptRequestDTO);
        
        QuizAttempt quizAttempt = quizAttemptMapper.toEntity(quizAttemptRequestDTO);
        
        quizAttempt = quizAttemptRepository.save(quizAttempt);
        
        logger.info("Quiz attempt saved with ID: {}", quizAttempt.getQuizAttemptId());
        
        eventPublisher.publishEvent(quizAttempt);
        logger.info("Published event for quiz attempt ID: {}", quizAttempt.getQuizAttemptId());
        
        return quizAttemptMapper.toResponseDTO(quizAttempt);
    }


    @Override
    public QuizAttemptResponseDTO getQuizAttemptById(Long attemptId) {
        logger.info("Fetching quiz attempt by ID: {}", attemptId);
        
        Optional<QuizAttempt> optionalQuizAttempt = quizAttemptRepository.findById(attemptId);
        if (optionalQuizAttempt.isEmpty()) {
        	
            logger.error("Quiz attempt not found for ID: {}", attemptId);
            throw new RuntimeException("Quiz attempt not found for ID: " + attemptId);
        }
        QuizAttempt quizAttempt = optionalQuizAttempt.get();
        logger.info("Quiz attempt found for ID: {}", attemptId);
        
        return quizAttemptMapper.toResponseDTO(quizAttempt);
    }

    @Override
    @Transactional
    public void deleteQuizAttempt(Long attemptId) {
    	
        logger.info("Deleting quiz attempt with ID: {}", attemptId);
        
        if (!quizAttemptRepository.existsById(attemptId)) {
        	
            logger.error("Quiz attempt not found for ID: {}", attemptId);
            throw new RuntimeException("Quiz attempt not found for ID: " + attemptId);
        }
        quizAttemptRepository.deleteById(attemptId);
        logger.info("Quiz attempt deleted for ID: {}", attemptId);
    }

    @Override
    public void processQuizAttempt(QuizAttempt quizAttempt) {
    	
        logger.info("Processing quiz attempt with ID: {}", quizAttempt.getQuizAttemptId());
        
        quizAttempt.setProcessed(true);
        
        quizAttemptRepository.save(quizAttempt);
        
        logger.info("Quiz attempt processed and saved for ID: {}", quizAttempt.getQuizAttemptId());
    }
}
