package com.springChat.infrastructure;

import com.authenticateService.api.AuthenticationServiceFacade;
import com.springChat.domain.model.Token;
import com.springChat.domain.model.User;
import com.springChat.domain.ports.UserAuthenticatorPort.UnauthorisedUserException;
import com.springChat.domain.ports.UserAuthenticatorPort.UserAuthenticator;
import com.springChat.domain.ports.UserAuthenticatorPort.UserNotExistException;
import com.springChat.domain.ports.UserReposytory;
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
