package com.example.quiz_attempt_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz_attempt_service.model.QuizResult;
import com.example.quiz_attempt_service.model.User;


@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {

    Optional<QuizResult> findByQuizAttemptId(Long quizAttemptId);

    // Update method to use the 'User' entity instead of userId
    Optional<QuizResult> findByUserAndQuizResultId(User user, Long quizResultId);  // Changed userId to User entity

    // If 'User' is a related entity, use the 'User' entity
    List<QuizResult> findByUser(User user);

    // Optional addition for finding by resultStatus
    List<QuizResult> findByResultStatus(String resultStatus); 
}
