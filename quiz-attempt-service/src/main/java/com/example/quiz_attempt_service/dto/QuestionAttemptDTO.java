package com.example.quiz_attempt_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAttemptDTO {

    private Long questionAttemptId;

    private Long quizAttemptId;

    private Long questionId;

    private String answer;

    private Boolean isCorrect;  // true if the answer is correct, false otherwise
}

