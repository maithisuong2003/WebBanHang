package com.example.demo.controller;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.dto.request.LogoutRequest;
import com.example.demo.dto.request.RefreshRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.dto.response.IntrospectResponse;
import com.example.demo.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    private AuthenticationService authenticationService;
    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login (@RequestBody AuthenticationRequest authenticationRequest){
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("Login successful")
                .result(result)
                .build();
    }
    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect (@RequestBody IntrospectRequest request)
    throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .message("Login successful")
                .result(result)
                .build();
    }
    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().message("Logout success").build();
    }
    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("Refresh successful!")
                .result(result)
                .build();
    }

}
