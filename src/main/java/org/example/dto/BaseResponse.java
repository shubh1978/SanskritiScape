package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class BaseResponse {

    private String status;

    private String message;

    public BaseResponse(String failure, String badRequest) {
        this.status=failure;
        this.message=badRequest;
    }
}