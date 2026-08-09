package com.orderms.user_service.service;

import com.orderms.user_service.dto.UserRequestDTO;
import com.orderms.user_service.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(Long Id);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUserById(Long Id, UserRequestDTO userRequestDTO);

    void deleteUserById(Long Id);
}
