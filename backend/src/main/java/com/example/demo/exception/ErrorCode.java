package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(1001, "User not found",HttpStatus.NOT_FOUND),
    USER_INACTIVE(1002, "User is inactive",HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1003, "Email already exists",HttpStatus.BAD_REQUEST),
    USER_EXISTED(1004, "Username already exists",HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1005, "Role not found",HttpStatus.NOT_FOUND),
    USER_ALREADY_INACTIVE(1006, "User is already inactive",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1011, "Unauthenticated",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(107, "You do not have permission",HttpStatus.FORBIDDEN),
    PERMISSION_NOT_EXITED(1020, "Permission Not Exited", HttpStatus.NOT_FOUND),
    ROLE_NOT_EXITED(1008, "Role Not Found", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(1009, "Invalid Token", HttpStatus.BAD_REQUEST),
    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
