package com.example.quiz_attempt_service.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.quiz_attempt_service.model.QuestionAttempt;
import com.example.quiz_attempt_service.model.QuizAttempt;

@Repository
public class QuestionAttemptRepository {

    private static final Logger logger = LoggerFactory.getLogger(QuestionAttemptRepository.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public QuestionAttempt save(QuestionAttempt questionAttempt) {
        logger.info("Saving question attempt for question ID: {}", questionAttempt.getQuestionId());

        String sql = "INSERT INTO question_attempt (question_id, "
                + "quiz_attempt_id, answer, attempt_time, is_correct) " +
                     "VALUES (:questionId, :quizAttemptId, :answer, :attemptTime, :isCorrect)";
        
        Map<String, Object> params = new HashMap<>();
        params.put("questionId", questionAttempt.getQuestionId());
        params.put("quizAttemptId", questionAttempt.getQuizAttempt().getQuizAttemptId()); 
        params.put("answer", questionAttempt.getAnswer());
        params.put("attemptTime", questionAttempt.getAttemptTime());
        params.put("isCorrect", questionAttempt.getIsCorrect());

        try {
            jdbcTemplate.update(sql, params);
            logger.info("Successfully saved question attempt for question ID: {}", questionAttempt.getQuestionId());
        } catch (Exception e) {
            logger.error("Error saving question attempt for question ID {}: {}", questionAttempt.getQuestionId(), e.getMessage());
            throw new RuntimeException("Error saving question attempt", e);
        }

        return questionAttempt;
    }

    // Change: Returning Optional<QuestionAttempt> instead of directly returning QuestionAttempt
    public Optional<QuestionAttempt> findByQuizAttemptIdAndQuestionId(Long quizAttemptId, Long questionId) {
        logger.info("Fetching question attempt with quizAttemptId: {} and questionId: {}", quizAttemptId, questionId);

        String sql = "SELECT * FROM question_attempt WHERE quiz_attempt_id = :quizAttemptId AND question_id = :questionId";
        Map<String, Object> params = new HashMap<>();
        params.put("quizAttemptId", quizAttemptId);
        params.put("questionId", questionId);

        try {
            QuestionAttempt attempt = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                QuestionAttempt questionAttempt = new QuestionAttempt();
                questionAttempt.setQuestionId(rs.getLong("question_id"));

                QuizAttempt quizAttempt = new QuizAttempt();
                quizAttempt.setQuizAttemptId(rs.getLong("quiz_attempt_id"));
                questionAttempt.setQuizAttempt(quizAttempt);

                questionAttempt.setAnswer(rs.getString("answer"));
                questionAttempt.setAttemptTime(rs.getTimestamp("attempt_time"));
                questionAttempt.setIsCorrect(rs.getBoolean("is_correct"));
                return questionAttempt;
            });

            // Wrap the result in an Optional
            return Optional.ofNullable(attempt);

        } catch (Exception e) {
            logger.error("Error fetching question attempt with quizAttemptId {} and questionId {}: {}", quizAttemptId, questionId, e.getMessage());
            return Optional.empty(); // Return empty Optional if no record is found
        }
    }

    public QuestionAttempt findById(Long questionId) {
        logger.info("Fetching question attempt with ID: {}", questionId);

        String sql = "SELECT * FROM question_attempt WHERE question_id = :questionId";
        Map<String, Object> params = new HashMap<>();
        params.put("questionId", questionId);

        try {
            return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                QuestionAttempt attempt = new QuestionAttempt();
                attempt.setQuestionId(rs.getLong("question_id"));

                QuizAttempt quizAttempt = new QuizAttempt();
                quizAttempt.setQuizAttemptId(rs.getLong("quiz_attempt_id"));
                attempt.setQuizAttempt(quizAttempt);

                attempt.setAnswer(rs.getString("answer"));
                attempt.setAttemptTime(rs.getTimestamp("attempt_time"));
                attempt.setIsCorrect(rs.getBoolean("is_correct"));
                return attempt;
            });
        } catch (Exception e) {
            logger.error("Error fetching question attempt with ID {}: {}", questionId, e.getMessage());
            return null;
        }
    }
}
