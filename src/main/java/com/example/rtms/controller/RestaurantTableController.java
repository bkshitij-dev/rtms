package com.example.rtms.controller;

import com.example.rtms.constant.AppConstants;
import com.example.rtms.dto.request.ActiveInactiveRequestDto;
import com.example.rtms.dto.request.ReservationRequestDto;
import com.example.rtms.dto.request.RestaurantTableRequestDto;
import com.example.rtms.dto.response.ApiResponse;
import com.example.rtms.service.RestaurantTableService;
import com.example.rtms.util.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
@Tag(name = "Restaurant Tables", description = "Apis related to Restaurant Tables")
@RestController
@RequestMapping("/api/v1/restaurant-tables")
@RequiredArgsConstructor
public class RestaurantTableController extends BaseController {

    private final RestaurantTableService restaurantTableService;

    @Operation(summary = "Create new restaurant table")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody RestaurantTableRequestDto request) {
        restaurantTableService.create(request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_SAVE), HttpStatus.OK);
    }

    @Operation(summary = "List all restaurant table")
    @GetMapping
    public ResponseEntity<ApiResponse> list(@RequestParam(name = "active", required = false) Boolean active) {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE,
                restaurantTableService.list(active)), HttpStatus.OK);
    }

    @Operation(summary = "Fetch restaurant table by id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, restaurantTableService.get(id)),
                HttpStatus.OK);
    }

    @Operation(summary = "Update existing restaurant table")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
                                              @RequestBody RestaurantTableRequestDto request) {
        restaurantTableService.update(id, request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_UPDATE), HttpStatus.OK);
    }

    @Operation(summary = "Remove existing restaurant table")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        restaurantTableService.delete(id);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_REMOVE), HttpStatus.OK);
    }

    @Operation(summary = "Activate/Deactivate restaurant table")
    @PutMapping("/{id}/toggle-active")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> toggleActive(@PathVariable("id") Long id,
                                                    @RequestBody ActiveInactiveRequestDto request) {
        restaurantTableService.toggleActive(id, request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_UPDATE), HttpStatus.OK);
    }
}
