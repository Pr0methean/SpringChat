package com.supertask.chat.vendors.AuthenticateServiceModule.domain.ports;

import com.supertask.chat.domain.model.User;

import java.util.List;

public interface TockenRepository {

    void saveToken(User user, String token);

    void deleteToken(User user);


    List<String> listOfTokens();

}
