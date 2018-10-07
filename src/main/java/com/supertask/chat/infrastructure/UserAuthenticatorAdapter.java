package com.supertask.chat.infrastructure;

import com.supertask.chat.domain.model.Token;
import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.ports.UserAuthenticatorPort.UnauthorisedUserException;
import com.supertask.chat.domain.ports.UserAuthenticatorPort.UserAuthenticator;
import com.supertask.chat.domain.ports.UserAuthenticatorPort.UserNotExistException;

public class UserAuthenticatorAdapter implements UserAuthenticator {



    @Override
    public User authenticateUserBy(String nick, String password) throws UserNotExistException {
        return null;
    }

    @Override
    public Token assignTokenToUser(User user) {
        return null;
    }

    @Override
    public User findUserByTocken(Token token) throws UnauthorisedUserException {
        return null;
    }
}
