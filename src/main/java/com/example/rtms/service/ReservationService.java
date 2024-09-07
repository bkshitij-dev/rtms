package com.example.rtms.service;

import com.example.rtms.dto.request.ReservationRequestDto;
import com.example.rtms.dto.request.StatusUpdateRequestDto;
import com.example.rtms.dto.response.ReservationDetailResponseDto;
import com.example.rtms.dto.response.ReservationResponseDto;

import java.util.List;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
public interface ReservationService {

    void create(ReservationRequestDto request);

    List<ReservationDetailResponseDto> list();

    ReservationDetailResponseDto get(Long id);

    void update(Long id, ReservationRequestDto request);

    void updateStatus(Long id, StatusUpdateRequestDto request);

    void delete(Long id);

    void completeReservation();

    long count();

    void reshuffleTables();
}
