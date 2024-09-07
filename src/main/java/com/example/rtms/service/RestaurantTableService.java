package com.example.rtms.service;

import com.example.rtms.dto.request.ActiveInactiveRequestDto;
import com.example.rtms.dto.request.RestaurantTableRequestDto;
import com.example.rtms.dto.response.RestaurantTableResponseDto;
import com.example.rtms.dto.response.WaitTimeResponseDto;
import com.example.rtms.model.RestaurantTable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
public interface RestaurantTableService {

    void create(RestaurantTableRequestDto request);

    List<RestaurantTableResponseDto> list(Boolean active);

    RestaurantTableResponseDto get(Long id);

    void update(Long id, RestaurantTableRequestDto request);

    void delete(Long id);

    void toggleActive(Long id, ActiveInactiveRequestDto request);

    Long getTableFitForPax(Timestamp timestamp, Integer pax);

    void updateStatus(Long id, String status);

    void updateStatusFromReservation(Long reservationId, String status);

    WaitTimeResponseDto getEarliestFreeTable(LocalDateTime requestedTime, Integer pax);

    long count();

    List<RestaurantTable> listAvailableTables();
}
