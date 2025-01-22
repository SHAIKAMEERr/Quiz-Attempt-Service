package com.example.quiz_attempt_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String role;
    
    private Boolean isActive;
    
}


