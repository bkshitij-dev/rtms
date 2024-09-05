package com.example.rtms.controller;

import com.example.rtms.constant.AppConstants;
import com.example.rtms.dto.request.LoginRequestDto;
import com.example.rtms.dto.request.RegisterRequestDto;
import com.example.rtms.dto.response.ApiResponse;
import com.example.rtms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseController {

    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequestDto request){
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, userService.login(request)),
                HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequestDto request) {
        userService.register(request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_SAVE), HttpStatus.OK);
    }
}
