package com.example.demo.service;
import com.example.demo.Entity.UserEntity;
import com.example.demo.bo.CreateUserRequest;
import com.example.demo.bo.UserResponse;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public interface UserService {
     List<UserEntity> getAllUsers();

     UserResponse createUser(CreateUserRequest request);
     UserResponse updateStatus(Long id, String status);

}
