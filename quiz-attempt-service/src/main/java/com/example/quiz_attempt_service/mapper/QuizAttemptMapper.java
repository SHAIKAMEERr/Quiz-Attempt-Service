package com.example.quiz_attempt_service.mapper;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.quiz_attempt_service.dto.QuizAttemptRequestDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptResponseDTO;
import com.example.quiz_attempt_service.model.QuizAttempt;

@Component
public class QuizAttemptMapper {

    @Autowired
    private ModelMapper modelMapper;

    public QuizAttempt toEntity(QuizAttemptRequestDTO quizAttemptRequestDTO) {
        return modelMapper.map(quizAttemptRequestDTO, QuizAttempt.class);
    }

    public QuizAttemptResponseDTO toResponseDTO(QuizAttempt quizAttempt) {
        return modelMapper.map(quizAttempt, QuizAttemptResponseDTO.class);
    }
}
