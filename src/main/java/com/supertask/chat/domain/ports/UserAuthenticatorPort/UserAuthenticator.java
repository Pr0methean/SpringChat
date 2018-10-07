package com.supertask.chat.domain.ports.UserAuthenticatorPort;

import com.supertask.chat.domain.model.Token;
import com.supertask.chat.domain.model.User;

public interface UserAuthenticator {

    /**
     * Method check if user exist in repository
     * @param nick nick from user
     * @param password password from user
     * @return User
     */
    User authenticateUserBy(String nick, String password) throws UserNotExistException;

    /**
     * @param user user
     * @return new token
     */
    Token assignTokenToUser( User user);

    /**
     * Find user by token
     * @param token token form cookie
     * @return User
     * @throws UnauthorisedUserException
     */
    User findUserByTocken(Token token) throws UnauthorisedUserException;


}
