package com.example.demo.service;

import com.example.demo.dto.request.PasswordChangeRequest;
import com.example.demo.dto.request.PasswordChangeRequest;
import com.example.demo.dto.request.PasswordResetRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse findUserById(Integer id);
    List<UserResponse> getAllUsers();
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Integer id,UserRequest userRequest);
    void deleteUser(Integer id);
    UserResponse getMyInfor();
    void editMyUser(UserRequest userRequest);
    void editMyPassword(PasswordChangeRequest passwordChangeRequest);
    void resetPassword(String token, String newPassword);
}
