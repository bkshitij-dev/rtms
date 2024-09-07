package com.example.rtms.controller;

import com.example.rtms.constant.AppConstants;
import com.example.rtms.dto.request.ReservationRequestDto;
import com.example.rtms.dto.request.StatusUpdateRequestDto;
import com.example.rtms.dto.response.ApiResponse;
import com.example.rtms.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
@Tag(name = "Reservations", description = "Apis related to Reservations")
@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController extends BaseController {

    private final ReservationService reservationService;

    @Operation(summary = "List all reservations")
    @GetMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ApiResponse> list() {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, reservationService.list()),
                HttpStatus.OK);
    }

    @Operation(summary = "Fetch reservation by id")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ApiResponse> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, reservationService.get(id)),
                HttpStatus.OK);
    }

    @Operation(summary = "Update existing reservation")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
                                              @RequestBody ReservationRequestDto request) {
        reservationService.update(id, request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_UPDATE), HttpStatus.OK);
    }

    @Operation(summary = "Update existing reservation status")
    @PutMapping("/{id}/updateStatus")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable("id") Long id,
                                              @RequestBody StatusUpdateRequestDto request) {
        reservationService.updateStatus(id, request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_UPDATE), HttpStatus.OK);
    }

    @Operation(summary = "Remove existing reservation")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        reservationService.delete(id);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_REMOVE), HttpStatus.OK);
    }
}
