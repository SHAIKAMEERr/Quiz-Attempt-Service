package com.example.quiz_attempt_service.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private static final Logger logger = LoggerFactory.getLogger(QuizAttemptServiceImpl.class);

    @Override
    public QuizAttemptResponseDTO createQuizAttempt(QuizAttemptRequestDTO quizAttemptRequestDTO) {
        logger.info("Creating new quiz attempt for userId: {}", quizAttemptRequestDTO.getUserId());
        QuizAttempt quizAttempt = quizAttemptMapper.toEntity(quizAttemptRequestDTO);
        QuizAttempt savedQuizAttempt = quizAttemptRepository.save(quizAttempt);
        return quizAttemptMapper.toDTO(savedQuizAttempt);
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
        logger.info("Starting quiz attempt with ID: {}", quizAttempt.getQuizId());
        // Logic for starting the quiz attempt
    }

    @Override
    public void calculateQuizResult(QuizAttempt quizAttempt) {
        logger.info("Calculating quiz result for quiz attempt with ID: {}", quizAttempt.getQuizId());
        // Logic for calculating the result
    }
}
