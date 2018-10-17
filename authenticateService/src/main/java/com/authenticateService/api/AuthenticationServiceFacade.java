package com.authenticateService.api;


import com.authenticateService.appliacation.dto.TokenDTO;
import com.authenticateService.appliacation.exceptions.Unauthorized;
import com.authenticateService.appliacation.services.TokenAuthorization;

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
