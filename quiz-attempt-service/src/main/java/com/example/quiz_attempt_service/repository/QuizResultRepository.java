package com.example.quiz_attempt_service.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.quiz_attempt_service.model.QuizResult;

@Repository
public class QuizResultRepository {

    private static final Logger logger = LoggerFactory.getLogger(QuizResultRepository.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public QuizResult save(QuizResult quizResult) {
        logger.info("Saving quiz result for quiz ID: {}", quizResult.getQuizId());

        String sql = "INSERT INTO quiz_result (quiz_id, user_id, total_score, result_time, score, total_questions, result_status) " +
                     "VALUES (:quizId, :userId, :totalScore, :resultTime, :score, :totalQuestions, :resultStatus)";
        Map<String, Object> params = new HashMap<>();
        params.put("quizId", quizResult.getQuizId());
        params.put("userId", quizResult.getUserId());
        params.put("totalScore", quizResult.getTotalScore());
        params.put("resultTime", quizResult.getResultTime());
        params.put("score", quizResult.getScore());
        params.put("totalQuestions", quizResult.getTotalQuestions());
        params.put("resultStatus", quizResult.getResultStatus());

        try {
            jdbcTemplate.update(sql, params);
            logger.info("Successfully saved quiz result for quiz ID: {}", quizResult.getQuizId());
        } catch (Exception e) {
            logger.error("Error saving quiz result for quiz ID {}: {}", quizResult.getQuizId(), e.getMessage());
            throw new RuntimeException("Error saving quiz result", e);
        }

        return quizResult;
    }

    public QuizResult findByQuizId(Long quizId) {
        logger.info("Fetching quiz result for quiz ID: {}", quizId);

        String sql = "SELECT * FROM quiz_result WHERE quiz_id = :quizId";
        Map<String, Object> params = new HashMap<>();
        params.put("quizId", quizId);

        try {
            return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                QuizResult result = new QuizResult();
                result.setQuizResultId(rs.getLong("quiz_result_id"));
                result.setQuizId(rs.getLong("quiz_id"));
                result.setUserId(rs.getLong("user_id"));
                result.setTotalScore(rs.getInt("total_score"));
                result.setResultTime(rs.getTimestamp("result_time"));
                result.setScore(rs.getInt("score"));
                result.setTotalQuestions(rs.getInt("total_questions"));
                result.setResultStatus(rs.getString("result_status"));
                return result;
            });
        } catch (Exception e) {
            logger.error("Error fetching quiz result for quiz ID {}: {}", quizId, e.getMessage());
            throw new RuntimeException("Error fetching quiz result", e);
        }
    }
    
    public Optional<QuizResult> findById(Long resultId) {
        logger.info("Fetching quiz result for result ID: {}", resultId);

        String sql = "SELECT * FROM quiz_result WHERE quiz_result_id = :resultId";
        Map<String, Object> params = new HashMap<>();
        params.put("resultId", resultId);

        try {
            QuizResult quizResult = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                QuizResult result = new QuizResult();
                result.setQuizResultId(rs.getLong("quiz_result_id"));
                result.setQuizId(rs.getLong("quiz_id"));
                result.setUserId(rs.getLong("user_id"));
                result.setTotalScore(rs.getInt("total_score"));
                result.setResultTime(rs.getTimestamp("result_time"));
                result.setScore(rs.getInt("score"));
                result.setTotalQuestions(rs.getInt("total_questions"));
                result.setResultStatus(rs.getString("result_status"));
                return result;
            });
            return Optional.ofNullable(quizResult);
        } catch (Exception e) {
            logger.error("Error fetching quiz result for result ID {}: {}", resultId, e.getMessage());
            return Optional.empty();
        }
    }

}
