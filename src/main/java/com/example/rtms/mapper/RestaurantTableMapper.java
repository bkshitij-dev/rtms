package com.example.rtms.mapper;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
import com.example.rtms.dto.request.RestaurantTableRequestDto;
import com.example.rtms.dto.response.RestaurantTableResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RestaurantTableMapper {

    List<RestaurantTableResponseDto> list();

    RestaurantTableResponseDto get(@Param("id") Long id);

    void update(@Param("id") Long id, @Param("request") RestaurantTableRequestDto request);
}
