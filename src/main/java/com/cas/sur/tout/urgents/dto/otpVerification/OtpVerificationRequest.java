package com.cas.sur.tout.urgents.dto.otpVerification;

import lombok.Data;

@Data
public class OtpVerificationRequest {
    private String phoneNumber;
    private String otpCode;
}
