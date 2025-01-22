package com.example.quiz_attempt_service.mapper;

import com.example.quiz_attempt_service.dto.QuestionAttemptDTO;
import com.example.quiz_attempt_service.model.QuestionAttempt;
import org.springframework.stereotype.Component;

@Component
public class QuestionAttemptMapper {

    public QuestionAttemptDTO toDTO(QuestionAttempt questionAttempt) {
        // Creating a DTO object from the entity
        QuestionAttemptDTO dto = new QuestionAttemptDTO();
        dto.setQuestionAttemptId(questionAttempt.getQuestionAttemptId());
        dto.setQuizAttemptId(questionAttempt.getQuizAttemptId());
        dto.setQuestionId(questionAttempt.getQuestionId());
        dto.setUserAnswer(questionAttempt.getUserAnswer());
        dto.setIsCorrect(questionAttempt.getIsCorrect());  // Added the isCorrect field
        dto.setAttemptTime(questionAttempt.getAttemptTime());  // Added the attemptTime field
        return dto;
    }

    public QuestionAttempt toEntity(QuestionAttemptDTO dto) {
        // Creating an entity object from the DTO
        QuestionAttempt questionAttempt = new QuestionAttempt();
        questionAttempt.setQuestionAttemptId(dto.getQuestionAttemptId());
        questionAttempt.setQuizAttemptId(dto.getQuizAttemptId());
        questionAttempt.setQuestionId(dto.getQuestionId());
        questionAttempt.setUserAnswer(dto.getUserAnswer());
        questionAttempt.setIsCorrect(dto.getIsCorrect());  // Added the isCorrect field
        questionAttempt.setAttemptTime(dto.getAttemptTime());  // Added the attemptTime field
        return questionAttempt;
    }
}
