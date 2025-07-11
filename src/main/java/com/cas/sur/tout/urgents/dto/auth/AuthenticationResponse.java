package com.cas.sur.tout.urgents.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

    private String accessToken;
    private String refreshToken;
    private UserInfo userInfo;
}
