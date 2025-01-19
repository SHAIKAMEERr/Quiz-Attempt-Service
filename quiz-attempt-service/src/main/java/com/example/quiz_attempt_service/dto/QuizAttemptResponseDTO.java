package com.example.quiz_attempt_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizAttemptResponseDTO {

    private Long quizAttemptId;

    private Long userId;

    private Long quizId;

    private String status;  // Example: IN_PROGRESS, COMPLETED, FAILED

    private String message;  // Additional message or error
}

