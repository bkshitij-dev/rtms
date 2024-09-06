package com.example.rtms.mapper;

import com.example.rtms.dto.response.StaffResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    void toggleStatus(@Param("id") Long id, @Param("active") boolean active);

    List<StaffResponseDto> listStaff(Boolean active);
}
