package com.example.rtms.service;

import com.example.rtms.dto.request.RoleRequestDto;
import com.example.rtms.enums.RoleType;
import com.example.rtms.model.Role;

public interface RoleService {

    void create(RoleRequestDto request);

    Role findByName(RoleType name);

    Long count();
}
