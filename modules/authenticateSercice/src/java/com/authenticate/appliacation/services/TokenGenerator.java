package com.authenticate.appliacation.services;


import com.authenticate.appliacation.dto.TokenDTO;

import java.time.Instant;
import java.util.Random;


public class TokenGenerator {


    public TokenDTO generate() {
        StringBuilder rawToken = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(55000);
            rawToken.append((char) number);
        }

        return new TokenDTO(rawToken.toString(), Instant.now(),Instant.now().plusNanos(1_000_000_000 * 60 * 5));


    }
}


