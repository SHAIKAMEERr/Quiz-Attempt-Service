package com.example.quiz_attempt_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz_attempt_service.model.QuestionAttempt;


@Repository
public interface QuestionAttemptRepository extends JpaRepository<QuestionAttempt, Long> {

    // Updated the second parameter from String to Long
    Optional<QuestionAttempt> findByQuizAttemptIdAndQuestionId(Long quizAttemptId, Long questionId);

    // Method to find all attempts by quizAttemptId
    List<QuestionAttempt> findByQuizAttemptId(Long quizAttemptId);
}
