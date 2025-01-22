package com.example.quiz_attempt_service.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScoringUtils {

    private static final Logger logger = LoggerFactory.getLogger(ScoringUtils.class);

    // Utility method to calculate the score for a quiz attempt
    public static int calculateScore(int correctAnswers, int totalQuestions) {
        if (totalQuestions == 0) {
            logger.warn("Total questions cannot be zero.");
            return 0; // Avoid division by zero
        }
        int score = (int) (((double) correctAnswers / totalQuestions) * 100); // Percentage score
        logger.info("Calculated score: {}%", score);
        return score;
    }

    // Utility method to calculate the passing score (e.g., 50% for passing)
    public static boolean isPassingScore(int score, int passingPercentage) {
        boolean isPassed = score >= passingPercentage;
        logger.info("Is passing score: {}", isPassed);
        return isPassed;
    }

    // Additional scoring-related logic can be added if needed
}
