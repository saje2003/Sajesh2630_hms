package com.hmsapp.config;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

        @Value("${twilio.accountSid}")
        private String accountSid;

        @Value("${twilio.authToken}")
        private String authToken;

        @Value("${twilio.phoneNumber}")
        private String twilioPhoneNumber;

    public void init() {
// Initialize the Twilio SDK with your account SID and Auth token
        Twilio.init(accountSid, authToken);
        System.out.println("Twilio SDK initialized.");
    }

    // Provide the Twilio phone number as a bean for later use
    public String getTwilioPhoneNumber() {
        return twilioPhoneNumber;
    }
}
