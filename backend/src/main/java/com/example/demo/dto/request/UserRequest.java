package com.example.demo.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserRequest {
    private String userName;
    private String fullName;
    private String email;
    private String password;
    private Set<String> roleNames;
    private String gender;
    private LocalDate birthday;
    private String address;
    private String phone;
}