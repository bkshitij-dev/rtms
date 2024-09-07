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
import com.example.rtms.mapper.ReservationRequestMapper;
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
    private final ReservationRequestMapper reservationRequestMapper;

    @Override
    @Transactional
    public void create(ReservationRequestRequestDto request) {
        LocalDateTime requestedDateTime = getReservationTime(request.getReservationRequestTime());

        ReservationRequestStatus status = ReservationRequestStatus.ASSIGNED;
        ReservationStatus reservationStatus = ReservationStatus.ARRIVED;
        if (requestedDateTime.isAfter(LocalDateTime.now())) {
            status = ReservationRequestStatus.ACKNOWLEDGED;
            reservationStatus = ReservationStatus.CONFIRMED;
        }

        ReservationRequest reservationRequest = ReservationRequest.builder()
                .customerName(request.getCustomerName())
                .customerEmail(request.getCustomerEmail())
                .customerContact(request.getCustomerContact())
                .pax(request.getPax())
                .reservationRequestTime(requestedDateTime)
                .status(status)
                .build();

        Long tableId = getAppropriateTable(requestedDateTime, reservationRequest, request.getPax());
        ReservationRequest reservationReq = reservationRequestRepository.save(reservationRequest);
        ReservationRequestDto requestDto = ReservationRequestDto.builder()
                .reservationRequestId(reservationReq.getId())
                .restaurantTableId(tableId)
                .reservationStartTime(DateUtil.getDateTimeString(requestedDateTime))
                .status(reservationStatus.name())
                .build();
        reservationService.create(requestDto);
    }

    private LocalDateTime getReservationTime(String reservationRequestTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime requestedDateTime = currentDateTime;
        if (reservationRequestTime != null) {
            requestedDateTime = DateUtil.getDateTime(reservationRequestTime);
            if (requestedDateTime.isBefore(currentDateTime)) {
                throw new AppException("Requested time is in the past");
            }
        }
        return requestedDateTime;
    }

    private Long getAppropriateTable(LocalDateTime requestedDateTime, ReservationRequest reservationRequest, int pax) {
        Long tableId = restaurantTableService.getTableFitForPax(Timestamp.valueOf(requestedDateTime), pax);

        if (tableId == null) {
            reservationRequest.setStatus(ReservationRequestStatus.PENDING);
            reservationRequestRepository.save(reservationRequest);
            WaitTimeResponseDto waitTimeResponse = restaurantTableService.getEarliestFreeTable(
                    requestedDateTime, pax);
            throw new AppException("No free table available for requested time. Next table available at: "
                    + waitTimeResponse.getAvailableTime()
                    + ". Estimated wait time is: " + waitTimeResponse.getWaitTime());
        }

        return tableId;
    }

    @Override
    public void updateStatus(Long id, StatusUpdateRequestDto request) {
        reservationRequestMapper.updateStatus(id, request.getStatus());
    }
}
