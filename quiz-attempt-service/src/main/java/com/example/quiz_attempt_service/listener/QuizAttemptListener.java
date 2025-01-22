package com.example.quiz_attempt_service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.quiz_attempt_service.model.QuizAttempt;
import com.example.quiz_attempt_service.service.QuizAttemptService;

@Component
public class QuizAttemptListener {

    private static final Logger logger = LoggerFactory.getLogger(QuizAttemptListener.class);

    private final QuizAttemptService quizAttemptService;

    public QuizAttemptListener(QuizAttemptService quizAttemptService) {
        this.quizAttemptService = quizAttemptService;
    }

    @EventListener
    public void handleQuizAttemptCreationEvent(QuizAttempt quizAttempt) {
        logger.info("Received quiz attempt creation event: {}", quizAttempt);
        try {
            quizAttemptService.startQuizAttempt(quizAttempt);
            logger.info("Quiz attempt started successfully for ID: {}", quizAttempt.getQuizId());
        } catch (Exception e) {
            logger.error("Error handling quiz attempt creation event", e);
        }
    }

    @EventListener
    public void handleQuizAttemptCompletionEvent(QuizAttempt quizAttempt) {
        logger.info("Received quiz attempt completion event: {}", quizAttempt);
        try {
            quizAttemptService.calculateQuizResult(quizAttempt);
            logger.info("Quiz attempt completed successfully for ID: {}", quizAttempt.getQuizId());
        } catch (Exception e) {
            logger.error("Error handling quiz attempt completion event", e);
        }
    }
}
