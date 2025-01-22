package com.example.quiz_attempt_service.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.quiz_attempt_service.dto.QuestionAttemptDTO;

@Component
public class QuestionValidator {

    private static final Logger logger = LoggerFactory.getLogger(QuestionValidator.class);

    // Validate if the QuestionAttemptDTO contains valid data
    public boolean validateQuestionAttempt(QuestionAttemptDTO questionAttemptDTO) {
        if (questionAttemptDTO == null) {
            logger.warn("Question attempt DTO is null.");
            return false;
        }

        if (questionAttemptDTO.getQuestionId() == null || questionAttemptDTO.getQuestionId()<=0) {
            logger.warn("Question ID is missing in the request.");
            return false;
        }

        if (questionAttemptDTO.getUserAnswer() == null || questionAttemptDTO.getUserAnswer().isEmpty()) {
            logger.warn("Answer is missing in the request.");
            return false;
        }

        // Additional validation checks can be added as required
        logger.info("Question attempt validation passed.");
        return true;
    }
}
