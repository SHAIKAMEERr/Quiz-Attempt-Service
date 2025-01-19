package com.example.quiz_attempt_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quiz_attempt")
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_attempt_id")
    private Long quizAttemptId; // Primary key for the quiz attempt

    @Column(name = "quiz_id", nullable = false)
    private Long quizId; // ID of the quiz being attempted

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User associated with the quiz attempt

    @Column(name = "score", nullable = false)
    private Integer score; // Score for the quiz attempt

    @Column(name = "attempt_time", nullable = false)
    private Timestamp attemptTime; // Time when the quiz was attempted

    @OneToMany(mappedBy = "quizAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionAttempt> questions; // Questions attempted in this quiz attempt

    @Column(name = "processed")
    private Boolean processed; // Processed status (nullable)

    @PrePersist
    public void prePersist() {
        // Ensure 'processed' is always initialized to false if null
        if (this.processed == null) {
            this.processed = false;
        }
    }

    // Optionally, you can add custom business logic for processing this attempt
    public void markAsProcessed() {
        this.processed = true;
    }

    // Other custom methods or business logic can be added here
}
