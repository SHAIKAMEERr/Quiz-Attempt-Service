USE quiz_attempt_db;

-- users
INSERT INTO users (username, password, email, first_name, last_name, date_of_birth) 
VALUES
    ('aravind123', 'password123', 'aravind@example.com', 'Aravind', 'Kumar', '1990-01-15'),
    ('priya.sharma', 'priya123', 'priya.sharma@gmail.com', 'Priya', 'Sharma', '1995-07-04'),
    ('vivek_patel', 'vivek123', 'vivek_patel@yahoo.com', 'Vivek', 'Patel', '1988-11-20');

-- quizzes
INSERT INTO quizzes (quiz_name, description, total_questions, duration)
VALUES
    ('Indian History Quiz', 'Test your knowledge of Indian history.', 10, 30),
    ('General Science Quiz', 'Questions on various science topics.', 15, 45),
    ('Programming Fundamentals', 'Test your basic programming skills.', 20, 60);

-- questions
INSERT INTO questions (quiz_id, question_text, correct_answer)
VALUES
    (1, 'Who was the first Prime Minister of India?', 'Jawaharlal Nehru'),
    (1, 'Which battle marked the end of Mughal rule in India?', 'Battle of Plassey'),
    (2, 'What is the chemical symbol for gold?', 'Au'),
    (2, 'Which planet is known as the Red Planet?', 'Mars'),
    (3, 'What does HTML stand for?', 'HyperText Markup Language'),
    (3, 'What is the purpose of CSS?', 'To style the presentation of HTML documents');

-- options
INSERT INTO options (question_id, option_text)
VALUES
    (1, 'Mahatma Gandhi'),
    (1, 'Jawaharlal Nehru'),
    (1, 'Sardar Patel'),
    (1, 'B.R. Ambedkar'),
    (2, 'Battle of Panipat'),
    (2, 'Battle of Plassey'),
    (2, 'Battle of Buxar'),
    (2, 'Second Battle of Panipat'),
    (3, 'Ag'),
    (3, 'Au'),
    (3, 'Hg'),
    (3, 'Fe'),
    (4, 'Mercury'),
    (4, 'Mars'),
    (4, 'Venus'),
    (4, 'Jupiter'),
    (5, 'HyperText Markup Language'),
    (5, 'High-Level Text Manipulation Language'),
    (5, 'Hyperlink and Text Markup Language'),
    (5, 'Home Tool Markup Language'),
    (6, 'To create interactive web pages'),
    (6, 'To style the presentation of HTML documents'),
    (6, 'To write server-side scripts'),
    (6, 'To store data in a database');

-- quiz_attempts (sample data - adjust based on actual quiz attempts)
INSERT INTO quiz_attempts (user_id, quiz_id, start_time, end_time, total_time_taken, score, total_questions, attempt_status, percentage, result_status)
VALUES
    (1, 1, '2024-07-04 10:00:00', '2024-07-04 10:25:00', 25.00, 8, 10, 'COMPLETED', 80.00, 'PASS'),
    (2, 2, '2024-07-05 15:30:00', '2024-07-05 16:18:00', 48.00, 12, 15, 'COMPLETED', 80.00, 'PASS'),
    (3, 3, '2024-07-06 20:00:00', '2024-07-06 21:30:00', 90.00, 15, 20, 'COMPLETED', 75.00, 'PASS');

-- question_attempts (sample data - adjust based on actual quiz attempts)
INSERT INTO question_attempts (attempt_id, question_id, user_answer, is_correct)
VALUES
    (1, 1, 'Jawaharlal Nehru', TRUE),
    (1, 2, 'Battle of Plassey', TRUE),
    (1, 3, 'Mahatma Gandhi', FALSE),
    (2, 4, 'Mars', TRUE),
    (2, 5, 'Au', TRUE),
    (3, 6, 'To style the presentation of HTML documents', TRUE),
    (3, 7, 'HyperText Markup Language', TRUE);

-- quiz_results (sample data - adjust based on actual quiz attempts)
INSERT INTO quiz_results (quiz_attempt_id, total_score, total_questions, percentage, result_status)
VALUES
    (1, 8, 10, 80.00, 'PASS'),
    (2, 12, 15, 80.00, 'PASS'),
    (3, 15, 20, 75.00, 'PASS');