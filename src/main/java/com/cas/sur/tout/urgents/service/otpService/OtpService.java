package com.cas.sur.tout.urgents.service.otpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {
    //private final SmsService smsService;
    private final ValidationService validationService;
    private final Map<String, OtpEntry> otpStorage = new ConcurrentHashMap<>();

    @Autowired
    public OtpService(ValidationService validationService) {
        this.validationService = validationService;
    }

    public String generateAndSendOtp(String phoneNumber) {
        try {
            // 2. Envoyer la requête à l’API externe
            double code = validationService.sendValidationRequest(phoneNumber)
                    .orElseThrow(() -> new RuntimeException("Échec lors de l'envoi de la requête OTP."));


            // Optionnel : Remplacer le code local par celui de l’API si besoin
            otpStorage.put(phoneNumber, new OtpEntry(String.valueOf((int) code), LocalDateTime.now().plusMinutes(5)));

            return "OTP envoyé avec succès à " + phoneNumber;
        } catch (Exception e) {
            throw new RuntimeException("Impossible d’envoyer le code OTP. Veuillez réessayer.");
        }
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
