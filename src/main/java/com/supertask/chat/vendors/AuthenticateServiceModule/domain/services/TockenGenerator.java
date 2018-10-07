package com.supertask.chat.vendors.AuthenticateServiceModule.domain.services;


import java.util.Random;


class TockenGenerator {


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


