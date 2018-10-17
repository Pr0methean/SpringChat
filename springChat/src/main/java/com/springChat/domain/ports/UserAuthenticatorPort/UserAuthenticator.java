package com.springChat.domain.ports.UserAuthenticatorPort;


import com.springChat.domain.model.Token;
import com.springChat.domain.model.User;

public interface UserAuthenticator {

    /**
     * Method check if user exist in repository
     * @param nick nick from user
     * @param password password from user
     * @return UserDTO
     */
    User authenticateUserBy(String nick, String password) throws UserNotExistException;

    /**
     * @param user user
     * @return new token
     */
    Token assignTokenToUser(User user);

    /**
     * Find user by token
     * @param token token form cookie
     * @return UserDTO
     * @throws UnauthorisedUserException
     */
    User findUserByToken(Token token) throws UnauthorisedUserException;


}
