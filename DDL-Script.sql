drop database if exists quiz_attempt_db;


CREATE DATABASE IF NOT EXISTS quiz_attempt_db;

-- Step 2: Use the newly created database
USE quiz_attempt_db;

-- Create the users table
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    date_of_birth DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create quizzes table
CREATE TABLE quizzes (
    quiz_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quiz_name VARCHAR(255) NOT NULL,
    description TEXT,
    total_questions INT NOT NULL,
    duration INT NOT NULL, -- Duration in minutes
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create the questions table
CREATE TABLE questions (
    question_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quiz_id BIGINT NOT NULL,
    question_text TEXT NOT NULL,
    correct_answer TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(quiz_id) ON DELETE CASCADE
);

-- Create the options table
CREATE TABLE options (
    option_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_id BIGINT NOT NULL,
    option_text TEXT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES questions(question_id) ON DELETE CASCADE
);

-- Create quiz_attempts table with updated column names
CREATE TABLE quiz_attempts (
    quiz_attempt_id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Matches entity field name
    user_id BIGINT NOT NULL,
    quiz_id BIGINT NOT NULL,
    start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_time TIMESTAMP,
    total_time_taken DOUBLE,
    score INT,
    total_questions INT,
    attempt_status VARCHAR(50) NOT NULL,  -- e.g., IN_PROGRESS, COMPLETED, FAILED
    percentage DOUBLE,
    result_status VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(quiz_id) ON DELETE CASCADE
);


-- Create question_attempts table
CREATE TABLE question_attempts (
    question_attempt_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    attempt_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    user_answer TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL,
    FOREIGN KEY (attempt_id) REFERENCES quiz_attempts(quiz_attempt_id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES questions(question_id) ON DELETE CASCADE
);

-- Create quiz_results table with updated column names
CREATE TABLE quiz_results (
    quiz_result_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quiz_attempt_id BIGINT NOT NULL,
    total_score INT,
    total_questions INT,
    percentage DOUBLE,
    result_status VARCHAR(50),
    FOREIGN KEY (quiz_attempt_id) REFERENCES quiz_attempts(quiz_attempt_id) ON DELETE CASCADE
);

-- Indexes
CREATE INDEX idx_user_id ON quiz_attempts (user_id);
CREATE INDEX idx_quiz_id ON quiz_attempts (quiz_id);
CREATE INDEX idx_quiz_result_status ON quiz_results (result_status);
