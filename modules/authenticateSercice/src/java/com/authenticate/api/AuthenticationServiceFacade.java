package com.authenticate.api;


import com.authenticate.appliacation.dto.TokenDTO;
import com.authenticate.appliacation.exceptions.Unauthorized;
import com.authenticate.appliacation.services.TokenAuthorization;

public class AuthenticationServiceFacade<T> {

    public static AuthenticationServiceFacade configure(){


        TokenAuthorization tokenAuthorization = TokenAuthorization.configure();

        return new AuthenticationServiceFacade(tokenAuthorization);
    }

    private TokenAuthorization<T> tokenAuthorization;

    private AuthenticationServiceFacade(TokenAuthorization tokenAuthorization) {
        this.tokenAuthorization = tokenAuthorization;
    }

    T authorize(TokenDTO token) throws Unauthorized {
        return tokenAuthorization.authorize(token);
    }

    public void assignToken(T object){
        tokenAuthorization.assignToken(object);
    }
}
