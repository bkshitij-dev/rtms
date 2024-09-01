package com.example.rtms.dto.response;

import com.example.rtms.enums.ResponseStatus;
import lombok.Builder;
import lombok.Data;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
@Data
@Builder
public class ApiResponse {

    private String message;
    private Object data;
    private ResponseStatus status;
}
