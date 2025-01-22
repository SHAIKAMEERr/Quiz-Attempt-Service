package com.example.quiz_attempt_service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.quiz_attempt_service.dto.QuizAttemptRequestDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptResponseDTO;
import com.example.quiz_attempt_service.model.QuizAttempt;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuizAttemptMapper {

    @Autowired
    private ModelMapper modelMapper;

    public QuizAttempt toEntity(QuizAttemptRequestDTO quizAttemptRequestDTO) {
        return modelMapper.map(quizAttemptRequestDTO, QuizAttempt.class);
    }
    
    public QuizAttemptResponseDTO toDTO(QuizAttempt quizAttempt) {
        QuizAttemptResponseDTO dto = new QuizAttemptResponseDTO();
        dto.setUserId(quizAttempt.getUserId());
        dto.setQuizId(quizAttempt.getQuizId());
        dto.setScore(quizAttempt.getScore());
        return dto;
    }

    public List<QuizAttemptResponseDTO> toDTOList(List<QuizAttempt> quizAttempts) {
        return quizAttempts.stream()
                           .map(this::toDTO)
                           .collect(Collectors.toList());
    }
}
