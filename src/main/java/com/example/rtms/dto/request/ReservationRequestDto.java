package com.example.rtms.dto.request;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequestDto {

    private Long reservationRequestId;
    private Long restaurantTableId;
    private String reservationStartTime;
    private String status;
}
