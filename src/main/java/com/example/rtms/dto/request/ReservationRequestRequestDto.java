package com.example.rtms.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Kshitij
 * @created 06-Sep-2024
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequestRequestDto {

    private String customerName;
    private String customerEmail;
    private String customerContact;
    private Integer pax;
    private String reservationRequestTime;
}
