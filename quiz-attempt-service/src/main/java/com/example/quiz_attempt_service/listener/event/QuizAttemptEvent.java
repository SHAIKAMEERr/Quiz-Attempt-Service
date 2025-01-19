package com.example.quiz_attempt_service.listener.event;

import org.springframework.context.ApplicationEvent;
import com.example.quiz_attempt_service.model.QuizAttempt;

public class QuizAttemptEvent extends ApplicationEvent {
    private final QuizAttempt quizAttempt;

    public QuizAttemptEvent(Object source, QuizAttempt quizAttempt) {
        super(source);
        this.quizAttempt = quizAttempt;
    }

    public QuizAttempt getQuizAttempt() {
        return quizAttempt;
    }
}
