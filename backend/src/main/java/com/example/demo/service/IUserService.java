package com.example.demo.service;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse findUserById(Integer id);
    List<UserResponse> getAllUsers();
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Integer id,UserRequest userRequest);
    void deleteUser(Integer id);
}
