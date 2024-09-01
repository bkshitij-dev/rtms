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
public class RestaurantTableResponseDto {

    private Long id;
    private String name;
    private Integer seatingCapacity;
    private String status;

}
