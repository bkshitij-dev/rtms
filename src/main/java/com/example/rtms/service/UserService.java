package com.example.rtms.service;

import com.example.rtms.dto.request.LoginRequestDto;
import com.example.rtms.dto.request.RegisterRequestDto;
import com.example.rtms.dto.response.LoginResponseDto;
import com.example.rtms.enums.RoleType;

import java.util.List;

public interface UserService {

    LoginResponseDto login(LoginRequestDto request);

    void create(RegisterRequestDto request, List<RoleType> roles);

    void register(RegisterRequestDto request);

    Long count();
}
