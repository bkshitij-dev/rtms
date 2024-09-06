package com.example.rtms.service;

import com.example.rtms.dto.request.ActiveInactiveRequestDto;
import com.example.rtms.dto.request.RestaurantTableRequestDto;
import com.example.rtms.dto.response.RestaurantTableResponseDto;

import java.time.LocalDateTime;
import java.util.List;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
public interface RestaurantTableService {

    void create(RestaurantTableRequestDto request);

    List<RestaurantTableResponseDto> list();

    RestaurantTableResponseDto get(Long id);

    void update(Long id, RestaurantTableRequestDto request);

    void delete(Long id);

    void toggleActive(Long id, ActiveInactiveRequestDto request);

    Long getTableFitForPax(Integer pax);

    void updateStatus(Long id, String status);

    void updateStatusFromReservation(Long reservationId, String status);

    String getNearestFreeTable(LocalDateTime requestedTime, Integer pax);

    long count();
}
