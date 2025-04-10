package com.hmsapp.service;

import com.hmsapp.config.TwilioConfig;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioService {

    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
        this.twilioConfig.init();
    }

    public void sendSms(String to, String body) {
        Message message = Message.creator(
                new PhoneNumber(to), // Destination phone number
                new PhoneNumber(twilioConfig.getTwilioPhoneNumber()), // Twilio phone number
                body // Message body
        ).create();

        System.out.println("Message sent with SID: " + message.getSid());
    }
}
