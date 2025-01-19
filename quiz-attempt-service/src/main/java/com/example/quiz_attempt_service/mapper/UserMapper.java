package com.example.quiz_attempt_service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.quiz_attempt_service.dto.UserDTO;
import com.example.quiz_attempt_service.model.User;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Mapping from User entity to UserDTO
    public UserDTO toUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    // Mapping from UserDTO to User entity
    public User toUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
