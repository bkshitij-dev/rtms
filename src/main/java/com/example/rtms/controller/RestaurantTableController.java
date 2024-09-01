package com.example.rtms.controller;

import com.example.rtms.constant.AppConstants;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
@RestController
@Tag(name = "Restaurant Tables", description = "Apis related to Restaurant Tables")
@RequestMapping("/api/v1/restaurant-tables")
@RequiredArgsConstructor
public class RestaurantTableController extends BaseController {

    private final RestaurantTableService restaurantTableService;

    @Operation(summary = "Create new restaurant table")
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody RestaurantTableRequestDto request) {
        restaurantTableService.create(request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_SAVE), HttpStatus.OK);
    }

    @Operation(summary = "List all restaurant table")
    @GetMapping
    public ResponseEntity<ApiResponse> list() {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, restaurantTableService.list()),
                HttpStatus.OK);
    }

    @Operation(summary = "Fetch restaurant table by id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, restaurantTableService.get(id)),
                HttpStatus.OK);
    }

    @Operation(summary = "Update existing restaurant table")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
                                              @RequestBody RestaurantTableRequestDto request) {
        restaurantTableService.update(id, request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_UPDATE), HttpStatus.OK);
    }

    @Operation(summary = "Remove existing restaurant table")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        restaurantTableService.delete(id);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_REMOVE), HttpStatus.OK);
    }

    @Operation(summary = "Get nearest free table")
    @PostMapping("/nearest-free")
    public ResponseEntity<ApiResponse> getNearestFreeTable(@RequestBody ReservationRequestDto request) {
        LocalDateTime dateTime = LocalDateTime.now();
        if (request.getReservationRequestTime() != null) {
            dateTime = DateUtil.getDateTime(request.getReservationRequestTime());
        }
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE,
                restaurantTableService.getNearestFreeTable(dateTime, request.getPax())), HttpStatus.OK);
    }
}
