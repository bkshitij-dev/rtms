package com.example.rtms.service.impl;

import com.example.rtms.dto.request.ReservationRequestDto;
import com.example.rtms.dto.request.StatusUpdateRequestDto;
import com.example.rtms.dto.response.ReservationResponseDto;
import com.example.rtms.enums.ReservationRequestStatus;
import com.example.rtms.enums.ReservationStatus;
import com.example.rtms.enums.TableStatus;
import com.example.rtms.exception.AppException;
import com.example.rtms.mapper.ReservationMapper;
import com.example.rtms.mapper.ReservationRequestMapper;
import com.example.rtms.model.Reservation;
import com.example.rtms.model.ReservationRequest;
import com.example.rtms.model.RestaurantTable;
import com.example.rtms.repository.ReservationRepository;
import com.example.rtms.repository.ReservationRequestRepository;
import com.example.rtms.repository.RestaurantTableRepository;
import com.example.rtms.service.ReservationService;
import com.example.rtms.service.RestaurantTableService;
import com.example.rtms.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final RestaurantTableService restaurantTableService;
    private final ReservationRepository reservationRepository;
    private final ReservationRequestRepository reservationRequestRepository;
    private final RestaurantTableRepository restaurantTableRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationRequestMapper reservationRequestMapper;

    @Override
    @Transactional
    public void create(ReservationRequestDto request) {
        ReservationRequest reservationRequest = reservationRequestRepository.findById(request.getReservationRequestId())
                .orElseThrow(() -> new AppException("Reservation request not found"));

        RestaurantTable restaurantTable = restaurantTableRepository.findById(request.getRestaurantTableId())
                .orElseThrow(() -> new AppException("Restaurant table not found"));

        LocalDateTime startDateTime = DateUtil.getDateTime(request.getReservationStartTime());
        Reservation reservation = Reservation.builder()
                .reservationRequest(reservationRequest)
                .restaurantTable(restaurantTable)
                .reservationStartTime(startDateTime)
                .reservationEndTime(DateUtil.getDateTimeOffset(startDateTime, 2))
                .status(ReservationStatus.valueOf(request.getStatus()))
                .build();
        reservationRepository.save(reservation);
        if (!startDateTime.isAfter(LocalDateTime.now())) {
            restaurantTableService.updateStatus(request.getRestaurantTableId(), TableStatus.OCCUPIED.name());
        }
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
    @Transactional
    public void updateStatus(Long id, StatusUpdateRequestDto request) {
        reservationMapper.updateStatus(id, request.getStatus());
        ReservationStatus reservationStatus = ReservationStatus.valueOf(request.getStatus());
        if (List.of(ReservationStatus.NO_SHOW, ReservationStatus.CANCELLED).contains(reservationStatus)) {
            reservationRequestMapper.updateStatusFromReservation(id, ReservationRequestStatus.CANCELLED.name());
            restaurantTableService.updateStatusFromReservation(id, TableStatus.AVAILABLE.name());
        } else if (reservationStatus.equals(ReservationStatus.ARRIVED)) {
            reservationRequestMapper.updateStatusFromReservation(id, ReservationRequestStatus.ASSIGNED.name());
            restaurantTableService.updateStatusFromReservation(id, TableStatus.OCCUPIED.name());
        } else if (reservationStatus.equals(ReservationStatus.COMPLETED)) {
            reservationRequestMapper.updateStatusFromReservation(id, ReservationRequestStatus.COMPLETED.name());
            restaurantTableService.updateStatusFromReservation(id, TableStatus.AVAILABLE.name());
        }
    }

    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void completeReservation() {
        List<Reservation> reservations = reservationRepository.findAllTimeExceeded();
        reservations.forEach(r -> {
            updateStatus(r.getId(), StatusUpdateRequestDto.builder().status(ReservationStatus.COMPLETED.name())
                    .build());
        });
    }

    @Override
    public long count() {
        return reservationRepository.count();
    }
}
