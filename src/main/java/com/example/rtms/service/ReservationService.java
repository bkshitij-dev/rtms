package com.example.rtms.service;

import com.example.rtms.dto.request.ReservationRequestDto;
import com.example.rtms.dto.request.RestaurantTableRequestDto;
import com.example.rtms.dto.response.ReservationResponseDto;
import com.example.rtms.dto.response.RestaurantTableResponseDto;

import java.util.List;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
public interface ReservationService {

    void create(ReservationRequestDto request);

    List<ReservationResponseDto> list();

    ReservationResponseDto get(Long id);

    void update(Long id, ReservationRequestDto request);

    void delete(Long id);
}
