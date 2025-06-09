package com.cas.sur.tout.urgents.service.otpService;

import com.cas.sur.tout.urgents.config.TwilioConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Service
public class SmsService {

    private final TwilioConfig twilioConfig;

    @Autowired
    public SmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public void sendSms(String phoneNumber, String message) {
        // Simulation ou intégration Twilio/Vonage
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
        Message.creator(
                new PhoneNumber(phoneNumber), // To
                new PhoneNumber(twilioConfig.getPhoneNumber()), // From (Twilio number)
                message
        ).create();

    }
}
