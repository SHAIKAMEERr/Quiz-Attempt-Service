package com.example.quiz_attempt_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_attempt_service.dto.QuizResultDTO;
import com.example.quiz_attempt_service.service.QuizResultService;

@RestController
@RequestMapping("/api/v1/quiz-results")
public class QuizResultController {

    @Autowired
    private QuizResultService quizResultService;

    // Generate quiz result for a quiz attempt
    @PostMapping("/{quizAttemptId}")
    public ResponseEntity<QuizResultDTO> generateQuizResult(@PathVariable Long quizAttemptId) {
        QuizResultDTO result = quizResultService.generateQuizResult(quizAttemptId);
        return ResponseEntity.status(201).body(result);
    }

    // Get quiz result by quiz attempt ID
    @GetMapping("/{quizAttemptId}")
    public ResponseEntity<QuizResultDTO> getQuizResultByQuizAttemptId(@PathVariable Long quizAttemptId) {
        return quizResultService.getQuizResultByQuizAttemptId(quizAttemptId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    // Get quiz result by user ID and quiz ID
    @GetMapping("/user/{userId}/quiz/{quizId}")
    public ResponseEntity<QuizResultDTO> getQuizResultByUserIdAndQuizId(@PathVariable String userId, @PathVariable String quizId) {
        return quizResultService.getQuizResultByUserIdAndQuizId(userId, quizId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }
}
