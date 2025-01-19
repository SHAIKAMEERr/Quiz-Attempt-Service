package com.example.quiz_attempt_service.service.impl;

import com.example.quiz_attempt_service.dto.QuestionAttemptDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptRequestDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptResponseDTO;
import com.example.quiz_attempt_service.exception.QuestionAttemptException;
import com.example.quiz_attempt_service.mapper.QuestionAttemptMapper;
import com.example.quiz_attempt_service.mapper.QuizAttemptMapper;
import com.example.quiz_attempt_service.model.QuestionAttempt;
import com.example.quiz_attempt_service.model.QuizAttempt;
import com.example.quiz_attempt_service.repository.QuestionAttemptRepository;
import com.example.quiz_attempt_service.repository.QuizAttemptRepository;
import com.example.quiz_attempt_service.service.QuestionAttemptService;
import com.example.quiz_attempt_service.validator.QuestionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionAttemptServiceImpl implements QuestionAttemptService {

    private static final Logger logger = LoggerFactory.getLogger(
    		QuestionAttemptServiceImpl.class);

    @Autowired
    private QuestionAttemptRepository questionAttemptRepository;

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    @Autowired
    private QuestionAttemptMapper questionAttemptMapper;

    @Autowired
    private QuizAttemptMapper quizAttemptMapper;

    @Autowired
    private QuestionValidator questionValidator;

    @Override
    public void attemptQuestion(Long attemptId, QuestionAttemptDTO 
    		questionAttemptDTO) {
        logger.info("Attempting question for quizAttemptId: {} and questionId:"
        		+ " {}", attemptId, questionAttemptDTO.getQuestionId());
        try {
            QuizAttempt quizAttempt = quizAttemptRepository.findById(attemptId)
                    .orElseThrow(() -> new QuestionAttemptException("Quiz attempt"
                    		+ " not found with ID: " + attemptId));

            QuestionAttempt questionAttempt = questionAttemptMapper.toEntity(questionAttemptDTO);
            questionAttempt.setQuizAttempt(quizAttempt);

            questionValidator.validateQuestionAttempt(questionAttempt);

            questionAttemptRepository.save(questionAttempt);

            logger.info("Successfully saved question attempt for questionId: {}", questionAttemptDTO.getQuestionId());
        } catch (Exception e) {
            logger.error("Error while attempting question: {}", e.getMessage(), e);
            throw new QuestionAttemptException("Error while attempting question", e);
        }
    }

    @Override
    public QuestionAttemptDTO getQuestionAttemptById(Long attemptId, Long questionId) {
        logger.info("Fetching question attempt for quizAttemptId: {} and questionId: {}", attemptId, questionId);
        try {
            QuestionAttempt questionAttempt = questionAttemptRepository
                    .findByQuizAttemptIdAndQuestionId(attemptId, questionId)
                    .orElseThrow(() -> new QuestionAttemptException(
                            "Question attempt not found for quizAttemptId: " + attemptId + 
                            " and questionId: " + questionId));

            return questionAttemptMapper.toDTO(questionAttempt);
        } catch (Exception e) {
            logger.error("Error fetching question attempt: {}", e.getMessage(), e);
            throw new QuestionAttemptException("Error fetching question attempt", e);
        }
    }


    @Override
    public QuizAttemptResponseDTO attemptQuiz(QuizAttemptRequestDTO quizAttemptRequestDTO) {
        logger.info("Attempting quiz for quizId: {}", quizAttemptRequestDTO.getQuizId());
        try {
            QuizAttempt quizAttempt = quizAttemptMapper.toEntity(quizAttemptRequestDTO);
            quizAttempt = quizAttemptRepository.save(quizAttempt);

            logger.info("Successfully saved quiz attempt with quizAttemptId: {}",
            		quizAttempt.getQuizAttemptId());

            return quizAttemptMapper.toResponseDTO(quizAttempt);
        } catch (Exception e) {
            logger.error("Error while attempting quiz: {}", e.getMessage(), e);
            throw new QuestionAttemptException("Error while attempting quiz", e);
        }
    }
}
