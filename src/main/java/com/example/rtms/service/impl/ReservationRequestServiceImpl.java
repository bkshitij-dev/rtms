package com.example.rtms.service.impl;

/*
 * @author Kshitij
 * @created 06-Sep-2024
 */

import com.example.rtms.dto.request.ReservationRequestDto;
import com.example.rtms.dto.request.ReservationRequestRequestDto;
import com.example.rtms.dto.request.StatusUpdateRequestDto;
import com.example.rtms.dto.response.ReservationRequestResponseDto;
import com.example.rtms.dto.response.WaitTimeResponseDto;
import com.example.rtms.enums.ReservationRequestStatus;
import com.example.rtms.enums.ReservationStatus;
import com.example.rtms.exception.AppException;
import com.example.rtms.model.ReservationRequest;
import com.example.rtms.repository.ReservationRequestRepository;
import com.example.rtms.service.ReservationRequestService;
import com.example.rtms.service.ReservationService;
import com.example.rtms.service.RestaurantTableService;
import com.example.rtms.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationRequestServiceImpl implements ReservationRequestService {

    private final RestaurantTableService restaurantTableService;
    private final ReservationService reservationService;
    private final ReservationRequestRepository reservationRequestRepository;

    @Override
    @Transactional
    public void create(ReservationRequestRequestDto request) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime requestedDateTime = currentDateTime;
        if (request.getReservationRequestTime() != null) {
            requestedDateTime = DateUtil.getDateTime(request.getReservationRequestTime());
            if (requestedDateTime.isBefore(currentDateTime)) {
                throw new AppException("Requested time is in the past");
            }
        }

        Long tableId = restaurantTableService.getTableFitForPax(Timestamp.valueOf(requestedDateTime), request.getPax());

        if (tableId == null) {
            WaitTimeResponseDto waitTimeResponse = restaurantTableService.getEarliestFreeTable(
                    requestedDateTime, request.getPax());
            throw new AppException("No free table available for requested time. Next table available at: "
                    + waitTimeResponse.getAvailableTime()
                    + ". Estimated wait time is: " + waitTimeResponse.getWaitTime());
        }

        ReservationRequestStatus status = ReservationRequestStatus.ASSIGNED;
        ReservationStatus reservationStatus = ReservationStatus.ARRIVED;
        if (requestedDateTime.isAfter(LocalDateTime.now())) {
            status = ReservationRequestStatus.PENDING;
            reservationStatus = ReservationStatus.REQUESTED;
        }
        ReservationRequest reservationRequest = ReservationRequest.builder()
                .customerName(request.getCustomerName())
                .customerEmail(request.getCustomerEmail())
                .customerContact(request.getCustomerContact())
                .pax(request.getPax())
                .reservationRequestTime(requestedDateTime)
                .status(status)
                .build();

        ReservationRequest reservationReq = reservationRequestRepository.save(reservationRequest);
        ReservationRequestDto requestDto = ReservationRequestDto.builder()
                .reservationRequestId(reservationReq.getId())
                .restaurantTableId(tableId)
                .reservationStartTime(DateUtil.getDateTimeString(requestedDateTime))
                .status(reservationStatus.name())
                .build();
        reservationService.create(requestDto);
    }

    @Override
    public List<ReservationRequestResponseDto> list() {
        return null;
    }

    @Override
    public ReservationRequestResponseDto get(Long id) {
        return null;
    }

    @Override
    public void update(Long id, ReservationRequestRequestDto request) {

    }

    @Override
    public void updateStatus(Long id, StatusUpdateRequestDto request) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void completeReservation() {

    }

    @Override
    public long count() {
        return 0;
    }
}
