package com.example.quiz_attempt_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;

    private String username;

    private String email;

    private String fullName;
}

