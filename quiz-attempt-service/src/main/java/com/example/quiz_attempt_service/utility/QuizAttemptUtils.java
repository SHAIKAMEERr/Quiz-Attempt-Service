package com.example.quiz_attempt_service.utility;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuizAttemptUtils {

    private static final Logger logger = LoggerFactory.getLogger(QuizAttemptUtils.class);

    // Utility method to calculate time remaining for the quiz attempt
    public static long calculateTimeRemaining(long startTime, long durationInMinutes) {
        long currentTime = System.currentTimeMillis();
        long endTime = startTime + TimeUnit.MINUTES.toMillis(durationInMinutes);
        long timeRemaining = endTime - currentTime;
        logger.info("Time remaining for quiz attempt: {} milliseconds", timeRemaining);
        return timeRemaining;
    }

    // Utility method to check if the quiz attempt is expired
    public static boolean isQuizExpired(long startTime, long durationInMinutes) {
        long timeRemaining = calculateTimeRemaining(startTime, durationInMinutes);
        boolean expired = timeRemaining <= 0;
        logger.info("Quiz expired: {}", expired);
        return expired;
    }

    // Additional utility methods can be added as needed
}
