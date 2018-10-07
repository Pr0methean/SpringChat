package com.supertask.chat.domain.services.AuthenticateServiceModule.domain.services;


import org.springframework.stereotype.Service;

import java.util.Random;


public class TockenGenerator {


    public StringBuilder generatrTOcken() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();


        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(55000);
            stringBuilder.append((char) number);
        }

        return stringBuilder;
    }
}


