package com.example.rtms.dto.response;

/*
 * @author Kshitij
 * @created 07-Sep-2024
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDetailResponseDto {

    private Long reservationId;
    private String customerName;
    private String customerEmail;
    private String customerContact;
    private Integer pax;
    private String reservationRequestStatus;
    private String reservationStartTime;
    private String reservationEndTime;
    private String reservationStatus;
}
