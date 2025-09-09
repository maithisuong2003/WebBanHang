package com.example.demo.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserResponse {
    private Integer id;
    private String userName;
    private String fullName;
    private String email;
    private Set<String> roles;
    private String gender;
    private LocalDate birthday;
    private String address;
    private String phone;
}