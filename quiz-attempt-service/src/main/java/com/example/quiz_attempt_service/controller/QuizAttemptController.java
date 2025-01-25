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

import com.example.quiz_attempt_service.dto.QuizAttemptRequestDTO;
import com.example.quiz_attempt_service.dto.QuizAttemptResponseDTO;
import com.example.quiz_attempt_service.mapper.QuizAttemptMapper;
import com.example.quiz_attempt_service.model.QuizAttempt;
import com.example.quiz_attempt_service.service.QuizAttemptService;

@RestController
@RequestMapping("/api/v1/quiz-attempts")
public class QuizAttemptController {

    @Autowired
    private QuizAttemptService quizAttemptService;

    @Autowired
    private QuizAttemptMapper quizAttemptMapper;

    // Create a new quiz attempt
    @PostMapping("/{quizId}")
    public ResponseEntity<QuizAttemptResponseDTO> createQuizAttempt(@RequestBody QuizAttemptRequestDTO quizAttemptRequestDTO) {
        QuizAttemptResponseDTO response = quizAttemptService.createQuizAttempt(quizAttemptRequestDTO);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizAttemptResponseDTO> getQuizAttemptById(@PathVariable Long id) {
        Optional<QuizAttempt> quizAttemptOptional = quizAttemptService.getQuizAttemptById(id);
        return quizAttemptOptional
            .map(quizAttempt -> {
                QuizAttemptResponseDTO responseDTO = quizAttemptMapper.toDTO(quizAttempt);
                return ResponseEntity.ok(responseDTO);
            })
            .orElseGet(() -> ResponseEntity.status(404).build());
    }
}
