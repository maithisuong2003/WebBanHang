package com.example.demo.controller;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.IUserService;
import lombok.RequiredArgsConstructor;
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
}