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
public class StatusUpdateRequestDto {

    private String status;
}
