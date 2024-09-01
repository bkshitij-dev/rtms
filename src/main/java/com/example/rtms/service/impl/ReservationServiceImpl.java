package com.example.rtms.service.impl;

import com.example.rtms.dto.request.ReservationRequestDto;
import com.example.rtms.dto.response.ReservationResponseDto;
import com.example.rtms.enums.ReservationStatus;
import com.example.rtms.enums.TableStatus;
import com.example.rtms.mapper.ReservationMapper;
import com.example.rtms.model.Reservation;
import com.example.rtms.model.RestaurantTable;
import com.example.rtms.repository.ReservationRepository;
import com.example.rtms.service.ReservationService;
import com.example.rtms.service.RestaurantTableService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final RestaurantTableService restaurantTableService;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    @Transactional
    public void create(ReservationRequestDto request) {
        Reservation reservation = Reservation.builder()
                .customerName(request.getCustomerName())
                .customerEmail(request.getCustomerEmail())
                .customerContact(request.getCustomerContact())
                .pax(request.getPax())
                .status(ReservationStatus.CONFIRMED)
                .build();
        Long tableId = restaurantTableService.getTableFitForPax(request.getPax());
        reservation.setRestaurantTable(RestaurantTable.builder().id(tableId).build());
        reservationRepository.save(reservation);
        restaurantTableService.updateStatus(tableId, TableStatus.RESERVED.name());
    }

    @Override
    public List<ReservationResponseDto> list() {
        return reservationMapper.list();
    }

    @Override
    public ReservationResponseDto get(Long id) {
        return reservationMapper.get(id);
    }

    @Override
    public void update(Long id, ReservationRequestDto request) {
        reservationMapper.update(id, request);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }
}
