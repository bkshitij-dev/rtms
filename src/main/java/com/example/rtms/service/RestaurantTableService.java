package com.example.rtms.service;

import com.example.rtms.dto.request.RestaurantTableRequestDto;
import com.example.rtms.dto.response.RestaurantTableResponseDto;
import com.example.rtms.enums.TableStatus;

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

    Long getTableFitForPax(Integer pax);

    void updateStatus(Long id, String status);
}
