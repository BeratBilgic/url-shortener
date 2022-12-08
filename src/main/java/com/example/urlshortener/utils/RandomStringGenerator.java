package com.example.urlshortener.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RandomStringGenerator {

    @Value("${codeLength}")
    private int codeLength;

    public String generateRandomString(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder sb = new StringBuilder();

        SecureRandom random = new SecureRandom();

        for(int i = 0; i < codeLength; i++) {

            char randomChar = chars.charAt(random.nextInt(chars.length()));

            sb.append(randomChar);
        }

        return sb.toString();
    }
}
