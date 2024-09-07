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
public class WaitTimeResponseDto {

    private String availableTime;
    private String waitTime;
}
