package com.authenticateService.domain.ports;


import com.authenticateService.appliacation.dto.TokenDTO;
import com.authenticateService.infrastructure.MemoryTockenRepository.exceptions.TokenNotFindException;

public interface TokenRepository<T> {

    public TokenDTO getToken(T object);

    public T findByToken(TokenDTO tokenDTO) throws TokenNotFindException;

    void saveToken(T object, TokenDTO tokenDTO);

    void deleteToken(T object);


}
