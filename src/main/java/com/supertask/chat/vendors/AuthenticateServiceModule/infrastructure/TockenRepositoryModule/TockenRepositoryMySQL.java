package com.supertask.chat.domain.services.AuthenticateServiceModule.infrastructure;

import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.services.AuthenticateServiceModule.domain.ports.TockenRepository;

import java.util.List;

public class TockenRepositoryMySQL implements TockenRepository {
    @Override
    public void saveToken(User user, String token) {

    }

    @Override
    public void deleteToken(User user) {

    }

    @Override
    public List<String> listOfTokens() {
        return null;
    }
}
