package com.example.quiz_attempt_service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.quiz_attempt_service.listener.event.QuizAttemptEvent;
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
    public void handleQuizAttemptEvent(QuizAttemptEvent event) {
    	
        QuizAttempt quizAttempt = event.getQuizAttempt();
        
        logger.info("Received quiz attempt event for QuizAttempt ID: {}",
        		quizAttempt.getQuizAttemptId());
        
        try
        {
            quizAttemptService.processQuizAttempt(quizAttempt);     
        } 
        catch (Exception e) 
        {
            logger.error("Error while processing quiz attempt event: {}", e.getMessage(), e);
        }
    }
}
