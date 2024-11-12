package com.example.demo.service;

import com.example.demo.Entity.UserEntity;
import com.example.demo.bo.CreateUserRequest;
import com.example.demo.bo.UserResponse;
import com.example.demo.controller.Status;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public UserResponse createUser(CreateUserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setStatus(Status.valueOf(request.getStatus()));
        userEntity = userRepository.save(userEntity);
        UserResponse response = new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getStatus().toString());
        return response;
    }

//logic here


    @Override
    public UserResponse updateStatus(Long id, String status) {
        if (!status.equalsIgnoreCase("Active") && !status.equalsIgnoreCase("Inactive")) {
            throw new IllegalArgumentException("Invalid status. Status must be either 'Active' or 'Inactive'.");
        }
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        userEntity.setStatus(Status.valueOf(status));
        userEntity = userRepository.save(userEntity);
        UserResponse response = new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getStatus().toString());
        return response;
    }

}

