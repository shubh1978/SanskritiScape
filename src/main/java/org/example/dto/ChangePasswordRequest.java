package org.example.dto;

import lombok.Data;

@Data

public class ChangePasswordRequest {

    private String email;
    private String currentPassword;
    private String newPassword;

    public String getEmail() {
        return email;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}