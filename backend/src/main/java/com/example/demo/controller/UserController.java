package com.example.demo.controller;

import com.example.demo.dto.request.PasswordChangeRequest;
import com.example.demo.dto.request.PasswordResetRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService iUserService;

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findUserById(@PathVariable Integer id){
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("Success")
                .result(iUserService.findUserById(id))
                .build();
    }
    @GetMapping(("/all"))
    @PreAuthorize("hasAuthority('USER_GET')")
    public ApiResponse<List<UserResponse>> getAllUsers(){
        return ApiResponse.<List<UserResponse>>builder()
                .code(200)
                .message("Success")
                .result(iUserService.getAllUsers())
                .build();
    }
    @PostMapping("register")
    public ApiResponse<UserResponse> createUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = iUserService.createUser(userRequest);
         return ApiResponse.<UserResponse>builder()
                 .code(200)
                 .message("Tao tai khoan thanh cong")
                 .build();

    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('USER_PUT')")
    public ApiResponse<UserResponse> updateUser(@PathVariable Integer id,@RequestBody UserRequest userRequest){
         UserResponse userResponse = iUserService.updateUser(id,userRequest);
         return ApiResponse.<UserResponse>builder()
                 .code(200)
                 .message("Success")
                 .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Integer id){
        iUserService.deleteUser(id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Xoa tai khoan thanh cong")
                .build();
    }
    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("Success")
                .result(iUserService.getMyInfor())
                .build();
    }
    @PutMapping("edit")
    public ApiResponse<String> editMyAccount(@RequestBody UserRequest userRequest) {
        iUserService.editMyUser(userRequest);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Success")
                .build();
    }
    @PutMapping("edit/password")
    public ApiResponse<String> editMyPassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        iUserService.editMyPassword(passwordChangeRequest);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Success")
                .build();
    }
    @PutMapping("/resetPassword")
    public ApiResponse<String> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        iUserService.resetPassword(passwordResetRequest.getToken(), passwordResetRequest.getNewPassword());
        return ApiResponse.<String>builder()
                .code(200)
                .message("Success")
                .build();
    }

}