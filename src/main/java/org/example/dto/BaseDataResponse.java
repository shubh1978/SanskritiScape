package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDataResponse {

    private String status;

    private Object data;

    public BaseDataResponse(String success, AuthToken signup) {
        this.status=success;
        this.data=signup;
    }
}