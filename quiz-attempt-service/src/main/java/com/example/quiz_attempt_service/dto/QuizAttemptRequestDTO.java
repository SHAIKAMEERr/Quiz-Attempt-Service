package com.example.quiz_attempt_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizAttemptRequestDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long quizId;

    private String status;  
}

