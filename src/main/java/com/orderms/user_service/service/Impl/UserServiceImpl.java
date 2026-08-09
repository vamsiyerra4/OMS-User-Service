package com.orderms.user_service.service.Impl;

import com.orderms.user_service.dto.UserRequestDTO;
import com.orderms.user_service.dto.UserResponseDTO;
import com.orderms.user_service.entity.User;
import com.orderms.user_service.exception.DuplicateEmailException;
import com.orderms.user_service.exception.UserNotFoundException;
import com.orderms.user_service.mapper.UserMapper;
import com.orderms.user_service.repository.UserRepository;
import com.orderms.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    


    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = userMapper.toEntity(userRequestDTO);

        if(userRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new DuplicateEmailException("Email already exists : " + userRequestDTO.getEmail());
        }
        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponseDTO getUserById(Long Id) {

        User user = userRepository.findById(Id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id :" + Id));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }


    public UserResponseDTO updateUserById(Long Id, UserRequestDTO userRequestDTO) {

        User user = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userRequestDTO.getPassword()));

        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    public void deleteUserById(Long Id) {

        User user = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);

    }
}
