package com.example.quiz_attempt_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz_attempt_service.model.QuizResult;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {

    Optional<QuizResult> findByQuizAttemptId(Long quizAttemptId);

    Optional<QuizResult> findByUserIdAndQuizId(Long userId, Long quizId);

    List<QuizResult> findByUserId(Long userId); // Optional addition
    List<QuizResult> findByResultStatus(String resultStatus); // Optional addition
}

