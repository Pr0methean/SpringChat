package com.authenticate.api;


import com.authenticate.domain.services.TokenAuthorization;
import com.authenticate.domain.services.UserAuthenticate;

public class AuthenticationServiceFacade {

    public static AuthenticationServiceFacade configure(){

        TokenAuthorization tokenAuthorization = new TokenAuthorization();
        UserAuthenticate userAuthenticate = new UserAuthenticate();

        return new AuthenticationServiceFacade(tokenAuthorization,userAuthenticate);
    }

    private TokenAuthorization tokenAuthorization;
    private UserAuthenticate userAuthenticate;

    private AuthenticationServiceFacade(TokenAuthorization tokenAuthorization, UserAuthenticate userAuthenticate) {
        this.tokenAuthorization = tokenAuthorization;
        this.userAuthenticate = userAuthenticate;
    }

    UserDTO findUserByTocken(TokenDTO token) {

        tokenAuthorization.assignTokenToUser();
        return new UserDTO();
    }
}
