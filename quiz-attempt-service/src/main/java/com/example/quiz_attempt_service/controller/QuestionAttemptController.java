package com.example.quiz_attempt_service.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_attempt_service.dto.QuestionAttemptDTO;
import com.example.quiz_attempt_service.service.QuestionAttemptService;

@RestController
@RequestMapping("/api/v1/question-attempts")
public class QuestionAttemptController {

    @Autowired
    private QuestionAttemptService questionAttemptService;

    @PostMapping("/{quizAttemptId}/{questionId}")
    public ResponseEntity<QuestionAttemptDTO> recordQuestionAttempt(@PathVariable Long quizAttemptId, @PathVariable Long questionId, @RequestBody String userAnswer) {
        QuestionAttemptDTO questionAttemptDTO = questionAttemptService.recordQuestionAttempt(quizAttemptId, questionId, userAnswer);
        return ResponseEntity.status(201).body(questionAttemptDTO);
    }

    @GetMapping("/{quizAttemptId}/{questionId}")
    public ResponseEntity<QuestionAttemptDTO> getQuestionAttempt(@PathVariable Long quizAttemptId, @PathVariable Long questionId) {
        Optional<QuestionAttemptDTO> questionAttemptOptional = questionAttemptService.getQuestionAttempt(quizAttemptId, questionId);
        return questionAttemptOptional
                .map(questionAttempt -> ResponseEntity.ok(questionAttempt))
                .orElseGet(() -> ResponseEntity.status(404).build());
    }
}
