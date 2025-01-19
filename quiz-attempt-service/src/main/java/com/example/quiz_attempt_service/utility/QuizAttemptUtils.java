package com.example.quiz_attempt_service.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuizAttemptUtils {

    private static final Logger logger = LoggerFactory.getLogger(QuizAttemptUtils.class);

    // Utility method to calculate total score
    public int calculateTotalScore(int correctAnswers, int totalQuestions) {
        logger.info("Calculating total score for {} correct answers out of {} questions.", correctAnswers, totalQuestions);
        if (totalQuestions == 0) {
            logger.error("Total questions cannot be zero.");
            throw new IllegalArgumentException("Total questions cannot be zero.");
        }
        int score = (int) (((double) correctAnswers / totalQuestions) * 100);
        logger.info("Calculated score: {}%", score);
        return score;
    }

    // Utility method to format score to display
    public String formatScore(int score) {
        logger.info("Formatting score: {}", score);
        return score + "%";
    }
}

