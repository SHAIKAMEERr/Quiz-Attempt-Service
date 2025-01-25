package com.example.quiz_attempt_service.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz_attempt_service.dto.QuizResultDTO;
import com.example.quiz_attempt_service.mapper.QuizResultMapper;
import com.example.quiz_attempt_service.model.QuestionAttempt;
import com.example.quiz_attempt_service.model.QuizResult;
import com.example.quiz_attempt_service.model.User;
import com.example.quiz_attempt_service.repository.QuizResultRepository;
import com.example.quiz_attempt_service.repository.UserRepository;
import com.example.quiz_attempt_service.service.QuestionAttemptService;
import com.example.quiz_attempt_service.service.QuizResultService;

@Service
public class QuizResultServiceImpl implements QuizResultService {

    private static final Logger logger = LoggerFactory.getLogger(
    		QuizResultServiceImpl.class);

    @Autowired
    private QuizResultRepository quizResultRepository;

    @Autowired
    private QuizResultMapper quizResultMapper;

    @Autowired
    private QuestionAttemptService questionAttemptService;
    
    @Autowired
    private UserRepository userRepository;

    private static final double PASSING_THRESHOLD = 50.0; // Pass percentage threshold

    @Override
    public QuizResultDTO generateQuizResult(Long quizAttemptId) {
        logger.info("Generating quiz result for quiz attempt ID: {}", quizAttemptId);

        // Step 1: Retrieve all question attempts for the quiz
        List<QuestionAttempt> questionAttempts = questionAttemptService
        		.getQuestionAttemptsByQuizAttemptId(quizAttemptId);

        if (questionAttempts.isEmpty()) {
            throw new RuntimeException("No question attempts found for quiz "
            		+ "attempt ID: " + quizAttemptId);
        }

        // Step 2: Calculate total score
        int totalQuestions = questionAttempts.size();
        int correctAnswers = 0;

        for (QuestionAttempt attempt : questionAttempts) {
            // Check if the user's answer matches the correct answer
            if (attempt.getUserAnswer() != null && attempt.getUserAnswer().
            		equalsIgnoreCase(attempt.getCorrectAnswer())) {
                correctAnswers++;
            }
        }

        // Step 3: Calculate percentage
        double scorePercentage = ((double) correctAnswers / totalQuestions) * 100;

        // Step 4: Determine status
        String status = scorePercentage >= PASSING_THRESHOLD ? "PASSED" : "FAILED";

        // Step 5: Save result (cast to integer if needed)
        QuizResult quizResult = new QuizResult();
        quizResult.setQuizAttemptId(quizAttemptId);
        quizResult.setTotalScore((int) scorePercentage);  // Cast to Integer
        quizResult.setPercentage(scorePercentage);  // Store percentage as Double
        quizResult.setResultStatus(status);

        quizResultRepository.save(quizResult);
        logger.info("Quiz result saved for quiz attempt ID: {}", quizAttemptId);

        // Step 6: Convert to DTO and return
        return quizResultMapper.toDTO(quizResult);
    }

    @Override
    public QuizResultDTO getQuizResultById(Long resultId) {
        logger.info("Fetching quiz result for result ID: {}", resultId);
        QuizResult quizResult = quizResultRepository.findById(resultId)
                .orElseThrow(() -> new RuntimeException("Quiz result not"
                		+ " found for ID: " + resultId));

        return quizResultMapper.toDTO(quizResult);
    }

    @Override
    public Optional<QuizResultDTO> getQuizResultByQuizAttemptId(Long quizAttemptId) {
        logger.info("Fetching quiz result for quiz attempt ID: {}", quizAttemptId);
        return quizResultRepository.findByQuizAttemptId(quizAttemptId)
                .map(quizResultMapper::toDTO);
    }

    @Override
    public Optional<QuizResultDTO> getQuizResultByUserIdAndQuizId(String userId, String quizId) {
        logger.info("Fetching quiz result for user ID: {} and quiz ID: {}", userId, quizId);
        try {
            // Convert userId and quizId from String to Long
            Long userIdLong = Long.parseLong(userId);
            Long quizIdLong = Long.parseLong(quizId);

            // Fetch the User entity based on userId
            Optional<User> userOptional = userRepository.findByUserId(userIdLong);
            
            if (userOptional.isPresent()) {
                // Call the repository method with the User object and quizIdLong
                return quizResultRepository.findByUserAndQuizResultId(userOptional.get(), quizIdLong)
                                           .map(quizResultMapper::toDTO);
            } else {
                logger.error("User not found with userId: {}", userId);
                return Optional.empty();
            }
        } catch (NumberFormatException e) {
            logger.error("Invalid format for userId or quizId. userId: {}, quizId: {}", userId, quizId, e);
            return Optional.empty();
        }
    }


}