package com.cas.sur.tout.urgents.controller.auth;

import com.cas.sur.tout.urgents.dto.auth.AuthenticationRequest;
import com.cas.sur.tout.urgents.dto.auth.AuthenticationResponse;
import com.cas.sur.tout.urgents.dto.auth.UserInfo;
import com.cas.sur.tout.urgents.service.auth.ApplicationUserDetails;
import com.cas.sur.tout.urgents.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.cas.sur.tout.urgents.utils.Constants.AUTH_ENDPOINT;

@RestController
@RequestMapping(AUTH_ENDPOINT)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authentication(@RequestBody AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getLogin(),
                            request.getPassword()
                    )
            );
            final ApplicationUserDetails userDetails = (ApplicationUserDetails) authentication.getPrincipal();

            final String jwt = jwtUtils.generateToken(userDetails);

            UserInfo userInfo = UserInfo.builder()
                    .id(userDetails.getId())
                    .username(userDetails.getUsername())
                    .emailOrTel(userDetails.getEmailOrtTel())
                    .role(userDetails.getAuthorities())
                    .build();

            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .accessToken(jwt)
                    .userInfo(userInfo)
                    .build()
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "message", "Les identifications sont erronées",
                    "error", "Non autorisé depuis controller"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "message", e.getMessage(),
                    "error", "Erreur interne"
            ));
        }

    }
}
