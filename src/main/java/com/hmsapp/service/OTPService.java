package com.hmsapp.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OTPService {

    private static final int OTP_EXPIRY_TIME = 5; // OTP expiry in minutes
    private Map<String, OTPDetails> otpStorage = new HashMap<>();

    // Method to generate OTP
    public String generateOTP(String mobileNumber) {
        // Generate a random 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));

        // Store OTP and its creation time in the map
        OTPDetails otpDetails = new OTPDetails(otp, System.currentTimeMillis());
        otpStorage.put(mobileNumber, otpDetails);

        return otp;
    }

    public boolean validateOTP(String mobileNumber, String inputOtp) {
        OTPDetails otpDetails = otpStorage.get(mobileNumber);

        if (otpDetails == null) {
            return false;
        }

        long currentTime = System.currentTimeMillis();
        long timeElapsed = (currentTime - otpDetails.getTimestamp()) / (60 * 1000); // convert to minutes

        if (timeElapsed > OTP_EXPIRY_TIME) {
            otpStorage.remove(mobileNumber);
            return false;
        }

        return otpDetails.getOtp().equals(inputOtp);
    }

    private static class OTPDetails {
        private String otp;
        private long timestamp;

        public OTPDetails(String otp, long timestamp) {
            this.otp = otp;
            this.timestamp = timestamp;
        }

        public String getOtp() {
            return otp;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}
