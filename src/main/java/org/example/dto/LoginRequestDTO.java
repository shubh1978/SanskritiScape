package org.example.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

//import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "username must  not be blank")
    private String email;

    @NotBlank(message = "password must  not be blank")
    private String password;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}