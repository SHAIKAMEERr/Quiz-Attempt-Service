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
                .resultId(quizResult.getQuizResultId()) 
                .attemptId(quizResult.getAttemptId())
                .score(quizResult.getScore())
                .totalQuestions(quizResult.getTotalQuestions())
                .resultStatus(quizResult.getResultStatus())
                .totalScore(quizResult.getTotalScore())
                .build();
    }

    public QuizResultDTO toQuizResultDTO(QuizResult quizResult) {
        return modelMapper.map(quizResult, QuizResultDTO.class);
    }

    public QuizResult toQuizResult(QuizResultDTO quizResultDTO) {
        return modelMapper.map(quizResultDTO, QuizResult.class);
    }
}
