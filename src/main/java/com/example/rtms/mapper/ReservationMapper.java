package com.example.rtms.mapper;

import com.example.rtms.dto.request.ReservationRequestDto;
import com.example.rtms.dto.request.RestaurantTableRequestDto;
import com.example.rtms.dto.response.ReservationResponseDto;
import com.example.rtms.dto.response.RestaurantTableResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
@Mapper
public interface ReservationMapper {

    List<ReservationResponseDto> list();

    ReservationResponseDto get(@Param("id") Long id);

    void update(@Param("id") Long id, @Param("request") ReservationRequestDto request);
}
