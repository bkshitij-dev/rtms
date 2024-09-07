package com.example.rtms.controller;

/*
 * @author Kshitij
 * @created 06-Sep-2024
 */

import com.example.rtms.constant.AppConstants;
import com.example.rtms.dto.request.ReservationRequestDto;
import com.example.rtms.dto.request.ReservationRequestRequestDto;
import com.example.rtms.dto.response.ApiResponse;
import com.example.rtms.service.ReservationRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Reservation Requests", description = "Apis related to Reservation Requests")
@RestController
@RequestMapping("/api/v1/reservation-requests")
@RequiredArgsConstructor
public class ReservationRequestController extends BaseController {

    private final ReservationRequestService reservationRequestService;

    @Operation(summary = "Create new reservation request")
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody ReservationRequestRequestDto request) {
        reservationRequestService.create(request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_SAVE), HttpStatus.OK);
    }
}
