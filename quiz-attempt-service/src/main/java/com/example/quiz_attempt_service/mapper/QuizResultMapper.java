package com.example.quiz_attempt_service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.quiz_attempt_service.dto.QuizResultDTO;
import com.example.quiz_attempt_service.model.QuizResult;

@Component
public class QuizResultMapper {

    @Autowired
    private ModelMapper modelMapper;

    public QuizResultDTO toDTO(QuizResult quizResult) {
        if (quizResult == null) {
            return null;
        }
        return QuizResultDTO.builder()
                .quizResultId(quizResult.getQuizResultId())
                .quizAttemptId(quizResult.getQuizAttemptId())
                .totalScore(quizResult.getTotalScore())
                .totalQuestions(quizResult.getTotalQuestions())
                .resultStatus(quizResult.getResultStatus())
                .build();
    }

    public QuizResult toEntity(QuizResultDTO quizResultDTO) {
        if (quizResultDTO == null) {
            return null;
        }
        return modelMapper.map(quizResultDTO, QuizResult.class);
    }
}
