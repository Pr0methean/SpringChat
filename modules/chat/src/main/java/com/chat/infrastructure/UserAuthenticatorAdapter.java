package com.chat.infrastructure;

import com.authenticate.api.AuthenticationServiceFacade;
import com.chat.domain.model.Token;
import com.chat.domain.model.User;
import com.chat.domain.ports.UserAuthenticatorPort.UnauthorisedUserException;
import com.chat.domain.ports.UserAuthenticatorPort.UserAuthenticator;
import com.chat.domain.ports.UserAuthenticatorPort.UserNotExistException;
import com.chat.domain.ports.UserReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticatorAdapter implements UserAuthenticator {

    private AuthenticationServiceFacade authenticationServiceFacade;


    @Autowired
    public UserAuthenticatorAdapter(UserReposytory userReposytory) {
        this.authenticationServiceFacade = AuthenticationServiceFacade.configure();
    }

    @Override
    public User authenticateUserBy(String nick, String password) throws UserNotExistException {
        return null;
    }

    @Override
    public Token assignTokenToUser(User user) {
        return null;
    }

    @Override
    public User findUserByToken(Token token) throws UnauthorisedUserException {
        return null;
    }
}
