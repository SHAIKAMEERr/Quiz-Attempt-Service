package com.example.quiz_attempt_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz_attempt_service.model.QuizAttempt;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

    Optional<QuizAttempt> findByUserIdAndQuizId(Long userId, Long quizId);

    List<QuizAttempt> findByUserId(Long userId); // Add pagination if required

}
