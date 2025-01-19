package com.example.quiz_attempt_service.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ScoringUtils {

    private static final Logger logger = LoggerFactory.getLogger(ScoringUtils.class);

    // Calculate the grading of a score
    public String gradeScore(int score) {
        logger.info("Grading score: {}", score);
        if (score >= 90) {
            logger.info("Grade A");
            return "A";
        } else if (score >= 80) {
            logger.info("Grade B");
            return "B";
        } else if (score >= 70) {
            logger.info("Grade C");
            return "C";
        } else if (score >= 50) {
            logger.info("Grade D");
            return "D";
        } else {
            logger.info("Grade F");
            return "F";
        }
    }
}

