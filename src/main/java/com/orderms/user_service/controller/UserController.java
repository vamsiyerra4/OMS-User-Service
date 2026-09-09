package com.orderms.user_service.controller;

import com.orderms.user_service.dto.UserRequestDTO;
import com.orderms.user_service.dto.UserResponseDTO;

import com.orderms.user_service.service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO response = userService.createUser(userRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserbyId(@PathVariable Long id){
        UserResponseDTO response = userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUser(){
        List<UserResponseDTO> response = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,@Valid @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO responseDTO = userService.updateUserById(id, userRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }


}
