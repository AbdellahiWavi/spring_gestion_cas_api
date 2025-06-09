package com.cas.sur.tout.urgents.controller.otpController;

import com.cas.sur.tout.urgents.dto.otpVerification.OtpRequest;
import com.cas.sur.tout.urgents.dto.otpVerification.OtpVerificationRequest;
import com.cas.sur.tout.urgents.service.otpService.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.cas.sur.tout.urgents.utils.Constants.OTP_ENDPOINT;

@RestController
@RequestMapping(OTP_ENDPOINT)
public class OtpController {
    private final OtpService otpService;

    @Autowired
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/request-otp")
    public ResponseEntity<Map<String, String>> requestOtp(@RequestBody OtpRequest request) {
        String result = otpService.generateAndSendOtp(request.getPhoneNumber());

        return ResponseEntity.ok(Map.of("message", result));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Map<String, String>> verifyOtp(@RequestBody OtpVerificationRequest request) {
        boolean isVerified = otpService.verifyOtp(request.getPhoneNumber(), request.getOtpCode());
        if (isVerified) {
            return ResponseEntity.ok(Map.of("message", "OTP valide"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "OTP invalide ou expiré."));
        }
    }
}
