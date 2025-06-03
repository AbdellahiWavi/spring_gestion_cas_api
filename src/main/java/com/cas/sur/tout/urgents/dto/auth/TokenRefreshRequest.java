package com.cas.sur.tout.urgents.dto.auth;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class TokenRefreshRequest {
    private String refreshToken;
}
