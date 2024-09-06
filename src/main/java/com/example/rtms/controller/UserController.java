package com.example.rtms.controller;

import com.example.rtms.constant.AppConstants;
import com.example.rtms.dto.request.ActiveInactiveRequestDto;
import com.example.rtms.dto.request.RegisterRequestDto;
import com.example.rtms.dto.response.ApiResponse;
import com.example.rtms.enums.RoleType;
import com.example.rtms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "Apis related to Users")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @Operation(summary = "Add staff")
    @PostMapping("/add-staff")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> addStaff(@RequestBody RegisterRequestDto request) {
        userService.create(request, List.of(RoleType.ROLE_STAFF));
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_SAVE), HttpStatus.OK);
    }

    @Operation(summary = "Toggle Status")
    @PutMapping("/{id}/toggle-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> toggleStatus(@PathVariable("id") Long id,
                                                    @RequestBody ActiveInactiveRequestDto request) {
        userService.toggleStatus(id, request);
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_UPDATE), HttpStatus.OK);
    }

    @Operation(summary = "List staff")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> listStaff(@RequestParam(name = "active", required = false) Boolean active) {
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE,
                userService.listStaff(active)), HttpStatus.OK);
    }
}
