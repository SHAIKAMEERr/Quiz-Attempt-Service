package com.example.quiz_attempt_service.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TimeUtils {

    private static final Logger logger = LoggerFactory.getLogger(TimeUtils.class);

    // Method to get current time formatted
    public String getCurrentTime() {
        logger.info("Fetching current time.");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());
        logger.info("Current time: {}", currentTime);
        return currentTime;
    }

    // Method to calculate remaining time for a quiz
    public String calculateRemainingTime(long endTimeInMillis) {
        logger.info("Calculating remaining time. End time: {}", endTimeInMillis);
        long remainingTime = endTimeInMillis - System.currentTimeMillis();
        if (remainingTime <= 0) {
            logger.info("Time is up.");
            return "Time is up!";
        }
        long minutes = remainingTime / (60 * 1000);
        long seconds = (remainingTime / 1000) % 60;
        String remainingTimeFormatted = String.format("%02d:%02d", minutes, seconds);
        logger.info("Remaining time: {}", remainingTimeFormatted);
        return remainingTimeFormatted;
    }
}
