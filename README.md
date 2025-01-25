# Quiz Attempt Service

The Quiz Attempt Service is a microservice for managing quiz attempts, question attempts, and quiz results. It adheres to RESTful API standards and is designed for scalability and maintainability.

## Features

- **Quiz Attempts:** Manage quiz attempts, including retrieval and updates.
- **Question Attempts:** Handle operations related to individual question attempts.
- **Quiz Results:** Fetch and compute quiz results.
- **Exception Handling:** Global exception handling for better error reporting.
- **Logging:** SLF4J-based logging for better traceability.
- **Validation:** Data validation for robust API behavior.

## Technologies Used

- **Java**: Core programming language.
- **Spring Boot**: Framework for building the application.
- **Spring Data JPA**: For database interaction.
- **ModelMapper**: For DTO to Entity mapping.
- **Lombok**: To reduce boilerplate code.
- **MySQL**: Database for persistent storage.
- **Swagger/OpenAPI**: API documentation.
- **SLF4J**: Logging framework.
- **H2**: Embedded database for testing.

## Prerequisites

Ensure you have the following installed:

- Java 17 or later
- Maven 3.6+
- MySQL Server

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```bash
   cd quiz-attempt-service
   ```

3. Update `application.properties` or `application.yml` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/quiz_db
   spring.datasource.username=root
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   ```

4. Build the application:
   ```bash
   mvn clean install
   ```

5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### Quiz Attempts

- **Get Quiz Attempt**
  ```
  GET /quiz-attempt/{quizAttemptId}
  ```
  Retrieves details of a specific quiz attempt.

- **Create Quiz Attempt**
  ```
  POST /quiz-attempt
  ```
  Creates a new quiz attempt.

### Question Attempts

- **Get Question Attempt**
  ```
  GET /quiz-attempt/{quizAttemptId}/{questionId}
  ```
  Retrieves details of a specific question attempt.

- **Update Question Attempt**
  ```
  PUT /quiz-attempt/{quizAttemptId}/{questionId}
  ```
  Updates the details of a question attempt.

### Quiz Results

- **Get Quiz Result**
  ```
  GET /quiz-attempt/{quizAttemptId}/result
  ```
  Retrieves the result of a specific quiz attempt.

## Error Handling

Custom exceptions are handled globally:

- `QuizAttemptException`
- `QuestionAttemptException`
- `QuizResultException`
- Generic `Exception`

Responses include timestamps, error messages, and details for easier debugging.

## Logging

SLF4J is used for logging with log levels such as INFO, WARN, and ERROR. Logs are written to the console and can be configured in `application.properties`.

## Testing

1. The application uses an embedded H2 database for testing.
2. Run tests using:
   ```bash
   mvn test
   ```

## Swagger Documentation

API documentation is available at:
```
http://localhost:8083/swagger-ui/index.html
```

## Contributing

Contributions are welcome! Please fork the repository and create a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For inquiries, please contact:

- **Email**: [Mail Me](shaikameerjan@gmail.com)
- **Linkedin**: [Linkedin](https://www.linkedin.com/in/ameer-shaikk/)
