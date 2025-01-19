package com.example.quiz_attempt_service.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.quiz_attempt_service.model.QuizAttempt;
import com.example.quiz_attempt_service.model.User;

@Repository
public class QuizAttemptRepository {

    private static final Logger logger = LoggerFactory.getLogger(QuizAttemptRepository.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public QuizAttempt save(QuizAttempt quizAttempt) {
        logger.info("Saving quiz attempt with ID: {}", quizAttempt.getQuizId());

        String sql = "INSERT INTO quiz_attempt (quiz_id, user_id, score, attempt_time) VALUES (:quizId, :userId, :score, :attemptTime)";
        Map<String, Object> params = new HashMap<>();
        params.put("quizId", quizAttempt.getQuizId());
        params.put("userId", quizAttempt.getUser().getUserId());
        params.put("score", quizAttempt.getScore());
        params.put("attemptTime", quizAttempt.getAttemptTime());

        try {
            jdbcTemplate.update(sql, params);
            logger.info("Successfully saved quiz attempt with ID: {}", quizAttempt.getQuizId());
        } catch (Exception e) {
            logger.error("Error saving quiz attempt with ID {}: {}", quizAttempt.getQuizId(), e.getMessage());
            throw new RuntimeException("Error saving quiz attempt", e);
        }

        return quizAttempt;
    }

    public Optional<QuizAttempt> findById(Long quizId) {
        logger.info("Fetching quiz attempt with ID: {}", quizId);

        String sql = "SELECT * FROM quiz_attempt WHERE quiz_attempt_id = :quizId";
        Map<String, Object> params = new HashMap<>();
        params.put("quizId", quizId);

        try {
            QuizAttempt attempt = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                QuizAttempt quizAttempt = new QuizAttempt();
                quizAttempt.setQuizAttemptId(rs.getLong("quiz_attempt_id"));
                quizAttempt.setQuizId(rs.getLong("quiz_id"));

                User user = new User();
                user.setUserId(rs.getLong("user_id"));
                quizAttempt.setUser(user);

                quizAttempt.setScore(rs.getInt("score"));
                quizAttempt.setAttemptTime(rs.getTimestamp("attempt_time"));
                return quizAttempt;
            });
            return Optional.ofNullable(attempt);
        } catch (Exception e) {
            logger.error("Error fetching quiz attempt with ID {}: {}", quizId, e.getMessage());
            return Optional.empty();
        }
    }

    public boolean existsById(Long quizAttemptId) {
        logger.info("Checking if quiz attempt exists with ID: {}", quizAttemptId);

        String sql = "SELECT COUNT(*) FROM quiz_attempt WHERE quiz_attempt_id = :quizAttemptId";
        Map<String, Object> params = new HashMap<>();
        params.put("quizAttemptId", quizAttemptId);

        Integer count = jdbcTemplate.queryForObject(sql, params, Integer.class);
        return count != null && count > 0;
    }

    public void deleteById(Long quizAttemptId) {
        logger.info("Deleting quiz attempt with ID: {}", quizAttemptId);

        String sql = "DELETE FROM quiz_attempt WHERE quiz_attempt_id = :quizAttemptId";
        Map<String, Object> params = new HashMap<>();
        params.put("quizAttemptId", quizAttemptId);

        try {
            jdbcTemplate.update(sql, params);
            logger.info("Successfully deleted quiz attempt with ID: {}", quizAttemptId);
        } catch (Exception e) {
            logger.error("Error deleting quiz attempt with ID {}: {}", quizAttemptId, e.getMessage());
            throw new RuntimeException("Error deleting quiz attempt", e);
        }
    }
}
