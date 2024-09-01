package com.example.rtms.service.impl;

import com.example.rtms.dto.request.ReservationRequestDto;
import com.example.rtms.dto.request.RestaurantTableRequestDto;
import com.example.rtms.dto.response.RestaurantTableResponseDto;
import com.example.rtms.enums.TableStatus;
import com.example.rtms.mapper.RestaurantTableMapper;
import com.example.rtms.model.RestaurantTable;
import com.example.rtms.repository.RestaurantTableRepository;
import com.example.rtms.service.RestaurantTableService;
import com.example.rtms.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */

@Service
@RequiredArgsConstructor
public class RestaurantTableServiceImpl implements RestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;
    private final RestaurantTableMapper restaurantTableMapper;

    @Override
    public void create(RestaurantTableRequestDto request) {
        TableStatus status = TableStatus.AVAILABLE;
        if (request.getStatus() != null) {
            status = TableStatus.valueOf(request.getStatus());
        }
        RestaurantTable restaurantTable = RestaurantTable.builder()
                .name(request.getName())
                .seatingCapacity(request.getSeatingCapacity())
                .status(status)
                .build();
        restaurantTableRepository.save(restaurantTable);
    }

    @Override
    public List<RestaurantTableResponseDto> list() {
        return restaurantTableMapper.list();
    }

    @Override
    public RestaurantTableResponseDto get(Long id) {
        return restaurantTableMapper.get(id);
    }

    @Override
    public void update(Long id, RestaurantTableRequestDto request) {
        restaurantTableMapper.update(id, request);
    }

    @Override
    public void delete(Long id) {
        restaurantTableRepository.deleteById(id);
    }

    @Override
    public Long getTableFitForPax(Integer pax) {
        return restaurantTableMapper.getTableFitForPax(pax);
    }

    @Override
    public void updateStatus(Long id, String status) {
        restaurantTableMapper.updateStatus(id, status);
    }

    @Override
    public void updateStatusFromReservation(Long reservationId, String status) {
        restaurantTableMapper.updateStatusFromReservation(reservationId, status);
    }

    @Override
    public String getNearestFreeTable(LocalDateTime requestedTime, Integer pax) {
        Timestamp timestamp = Timestamp.valueOf(requestedTime);
        String timeString = restaurantTableMapper.getNearestFreeTable(timestamp, pax);
        return DateUtil.getTimeDifference(DateUtil.getDateTimeString(requestedTime, DateUtil.ISO_FULL_DATE_TIME_FORMAT),
                timeString);
    }
}
