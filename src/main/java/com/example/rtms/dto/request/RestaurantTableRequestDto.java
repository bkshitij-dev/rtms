package com.example.rtms.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTableRequestDto {

    private String name;
    private Integer seatingCapacity;
    private String status;
    private boolean active;

}
