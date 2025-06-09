package com.cas.sur.tout.urgents.service.otpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {
    private final SmsService smsService;
    private final Map<String, OtpEntry> otpStorage = new ConcurrentHashMap<>();

    @Autowired
    public OtpService(SmsService smsService) {
        this.smsService = smsService;
    }

    public String generateAndSendOtp(String phoneNumber) {
        String otp = String.format("%06d", new SecureRandom().nextInt(999999));
        otpStorage.put(phoneNumber, new OtpEntry(otp, LocalDateTime.now().plusMinutes(5)));
        smsService.sendSms(phoneNumber, "Votre code de vérification LISAMA est : " + otp);
        return "OTP envoyé !";
    }

    public boolean verifyOtp(String phoneNumber, String code) {
        OtpEntry entry = otpStorage.get(phoneNumber);
        if (entry == null || entry.expiry.isBefore(LocalDateTime.now())) {
            return false;
        }
        return entry.otp.equals(code);
    }

    private static class OtpEntry {
        String otp;
        LocalDateTime expiry;

        OtpEntry(String otp, LocalDateTime expiry) {
            this.otp = otp;
            this.expiry = expiry;
        }
    }
}
