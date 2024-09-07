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

//    @Operation(summary = "List all reservation requests")
//    @GetMapping
//    @PreAuthorize("hasRole('STAFF')")
//    public ResponseEntity<ApiResponse> list() {
//        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, reservationRequestService.list()),
//                HttpStatus.OK);
//    }
//
//    @Operation(summary = "Fetch reservation request by id")
//    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('STAFF')")
//    public ResponseEntity<ApiResponse> get(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, reservationService.get(id)),
//                HttpStatus.OK);
//    }
//
//    @Operation(summary = "Update existing reservation request")
//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('STAFF')")
//    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
//                                              @RequestBody ReservationRequestRequestDto request) {
//        reservationRequestService.update(id, request);
//        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_UPDATE), HttpStatus.OK);
//    }
//
//    @Operation(summary = "Update existing reservation status")
//    @PutMapping("/{id}/updateStatus")
//    @PreAuthorize("hasRole('STAFF')")
//    public ResponseEntity<ApiResponse> updateStatus(@PathVariable("id") Long id,
//                                                    @RequestBody StatusUpdateRequestDto request) {
//        reservationService.updateStatus(id, request);
//        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_UPDATE), HttpStatus.OK);
//    }
//
//    @Operation(summary = "Remove existing reservation")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
//        reservationService.delete(id);
//        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_REMOVE), HttpStatus.OK);
//    }
}
