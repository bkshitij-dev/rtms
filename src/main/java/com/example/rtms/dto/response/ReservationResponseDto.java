package com.example.rtms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponseDto {

    private Long id;
    private ReservationRequestResponseDto reservationRequest;
    private RestaurantTableResponseDto restaurantTable;
    private String reservationStartTime;
    private String reservationEndTime;
    private String status;

}

