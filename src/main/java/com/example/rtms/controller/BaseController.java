package com.example.rtms.controller;

import com.example.rtms.dto.response.ApiResponse;
import com.example.rtms.enums.ResponseStatus;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
public class BaseController {

    protected ApiResponse successResponse(String message, Object data) {
        return ApiResponse.builder()
                .status(ResponseStatus.SUCCESS)
                .message(message)
                .data(data)
                .build();
    }

    protected ApiResponse successResponse(String message) {
        return successResponse(message, null);
    }

    protected ApiResponse errorResponse(String message, Object data) {
        return ApiResponse.builder()
                .status(ResponseStatus.FAILURE)
                .message(message)
                .data(data)
                .build();
    }
}
