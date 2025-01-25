package com.example.quiz_attempt_service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.quiz_attempt_service.dto.QuizAttemptRequestDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptResponseDTO;
import com.example.quiz_attempt_service.model.QuizAttempt;

@Component
public class QuizAttemptMapper {

    public QuizAttempt toEntity(QuizAttemptRequestDTO requestDTO) {
        QuizAttempt quizAttempt = new QuizAttempt();
        quizAttempt.setQuizId(requestDTO.getQuizId());
        quizAttempt.setUserId(requestDTO.getUserId());
        return quizAttempt;
    }

    public QuizAttemptResponseDTO toDTO(QuizAttempt quizAttempt) {
        return new QuizAttemptResponseDTO(
                quizAttempt.getQuizAttemptId(),
                quizAttempt.getQuizId(),
                quizAttempt.getUserId(),
                quizAttempt.getStartTime(),
                quizAttempt.getEndTime(),
                quizAttempt.getScore(),
                quizAttempt.getTotalQuestions(),
                quizAttempt.getAttemptStatus(),
                quizAttempt.getPercentage(),
                quizAttempt.getResultStatus(),
                quizAttempt.getTotalTimeTaken()
        );
    }

    public List<QuizAttemptResponseDTO> toDTOList(List<QuizAttempt> quizAttempts) {
        return quizAttempts.stream().map(this::toDTO).collect(Collectors.toList());
    }
}