package com.example.quiz_attempt_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.quiz_attempt_service.repository")
public class QuizAttemptServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizAttemptServiceApplication.class, args);
	}

}
