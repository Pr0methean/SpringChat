package com.authenticate.domain.ports;

import java.util.List;

public interface TockenRepository {

    void saveToken(User user, String token);

    void deleteToken(User user);


    List<String> listOfTokens();

}
