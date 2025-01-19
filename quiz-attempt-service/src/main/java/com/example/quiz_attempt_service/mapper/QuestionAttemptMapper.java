package com.example.quiz_attempt_service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.quiz_attempt_service.dto.QuestionAttemptDTO;
import com.example.quiz_attempt_service.model.QuestionAttempt;

@Component
public class QuestionAttemptMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Mapping from QuestionAttempt entity to QuestionAttemptDTO
    public QuestionAttemptDTO toDTO(QuestionAttempt questionAttempt) {
        return modelMapper.map(questionAttempt, QuestionAttemptDTO.class);
    }

    // Mapping from QuestionAttemptDTO to QuestionAttempt entity
    public QuestionAttempt toEntity(QuestionAttemptDTO questionAttemptDTO) {  // Renamed to toEntity
        return modelMapper.map(questionAttemptDTO, QuestionAttempt.class);
    }
}
