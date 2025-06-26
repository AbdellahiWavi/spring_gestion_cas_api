package com.cas.sur.tout.urgents.controller.auth;

import com.cas.sur.tout.urgents.dto.auth.TokenRefreshRequest;
import com.cas.sur.tout.urgents.dto.auth.TokenRefreshResponse;
import com.cas.sur.tout.urgents.model.RefreshToken;
import com.cas.sur.tout.urgents.service.auth.ApplicationUserDetails;
import com.cas.sur.tout.urgents.service.auth.ApplicationUserDetailsService;
import com.cas.sur.tout.urgents.service.auth.RefreshTokenService;
import com.cas.sur.tout.urgents.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cas.sur.tout.urgents.utils.Constants.AUTH_ENDPOINT;

@RestController
@RequestMapping(AUTH_ENDPOINT)
public class AuthRefreshController {

    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    @Autowired
    private JwtUtil jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getClient)
                .map(client -> {
                    ApplicationUserDetails userDetails =
                            (ApplicationUserDetails) applicationUserDetailsService.loadUserByUsername(client.getTel());
                    String newAccessToken = jwtService.generateToken(userDetails);
                    String newRefreshToken = refreshTokenService.createRefreshToken(client.getId()).getToken(); // optionnel
                    return ResponseEntity.ok(TokenRefreshResponse.builder()
                            .accessToken(newAccessToken)
                            .refreshToken(newRefreshToken)
                            .build()
                    );
                })
                .orElseThrow(() -> new RuntimeException("Refresh token n'est pas valide!"));
    }
}
