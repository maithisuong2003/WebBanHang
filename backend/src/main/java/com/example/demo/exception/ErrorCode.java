package com.example.demo.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    USER_NOT_FOUND(1001, "User not found"),
    USER_INACTIVE(1002, "User is inactive"),
    EMAIL_EXISTED(1003, "Email already exists"),
    USER_EXISTED(1004, "Username already exists"),
    ROLE_NOT_FOUND(1005, "Role not found"),
    USER_ALREADY_INACTIVE(1006, "User is already inactive"),
    UNAUTHENTICATED(1011, "Unauthenticated")
    ;
    private int code;
    private String message;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
