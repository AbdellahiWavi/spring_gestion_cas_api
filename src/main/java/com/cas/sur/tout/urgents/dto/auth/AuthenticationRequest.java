package com.cas.sur.tout.urgents.dto.auth;

import lombok.*;

@Data
@Builder
public class AuthenticationRequest {

    private String login;

    private String password;

}
