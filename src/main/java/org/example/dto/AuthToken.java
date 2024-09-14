package org.example.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.Authentication;

import java.util.Optional;

@Data
@Builder
public class AuthToken {
    private final String token;
    private final Authentication authentication;
    private final long createdOn;

    public AuthToken(String token, Authentication authentication, long createdOn) {
        this.token = token;
        this.authentication = authentication;
        this.createdOn = createdOn;
    }


}