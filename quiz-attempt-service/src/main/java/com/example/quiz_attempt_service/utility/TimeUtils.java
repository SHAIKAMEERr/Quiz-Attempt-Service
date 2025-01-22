package com.example.quiz_attempt_service.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtils {

    private static final Logger logger = LoggerFactory.getLogger(TimeUtils.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Utility method to format the current time as a string
    public static String formatCurrentTime() {
        String formattedTime = dateFormat.format(new Date());
        logger.info("Formatted current time: {}", formattedTime);
        return formattedTime;
    }

    // Utility method to get the time difference in hours between two timestamps
    public static long calculateTimeDifferenceInHours(long startTime, long endTime) {
        long timeDifference = (endTime - startTime) / (1000 * 60 * 60); // Returns difference in hours
        logger.info("Time difference in hours: {}", timeDifference);
        return timeDifference;
    }

    // Additional time-related utility methods can be added as needed
}
