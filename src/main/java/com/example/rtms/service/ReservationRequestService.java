package com.example.rtms.service;

import com.example.rtms.dto.request.ReservationRequestDto;
import com.example.rtms.dto.request.ReservationRequestRequestDto;
import com.example.rtms.dto.request.StatusUpdateRequestDto;
import com.example.rtms.dto.response.ReservationRequestResponseDto;
import com.example.rtms.dto.response.ReservationResponseDto;

import java.util.List;

/*
 * @author Kshitij
 * @created 06-Sep-2024
 */
public interface ReservationRequestService {

    void create(ReservationRequestRequestDto request);

    void updateStatus(Long id, StatusUpdateRequestDto request);
}
