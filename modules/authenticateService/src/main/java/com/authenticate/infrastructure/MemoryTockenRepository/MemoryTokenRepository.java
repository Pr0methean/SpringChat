package com.authenticate.infrastructure.MemoryTockenRepository;

import com.authenticate.appliacation.dto.TokenDTO;
import com.authenticate.domain.ports.TokenRepository;
import com.authenticate.infrastructure.MemoryTockenRepository.exceptions.TokenNotFindException;

import java.time.Instant;
import java.util.*;

public class MemoryTokenRepository<T> implements TokenRepository<T> {


    Map<TokenDTO, T> tokensMap = new TreeMap<>();
    Map<T, TokenDTO> ObjectMap = new TreeMap<>();

    @Override
    public TokenDTO getToken( T object){
        return ObjectMap.get(object);
    }

    @Override
    public T findByToken(TokenDTO tokenDTO) throws TokenNotFindException {
        if (tokensMap.containsKey(tokenDTO)) {
            return tokensMap.get(tokenDTO);
        } else {
            throw new TokenNotFindException("Token not find in Repository");
        }
    }


    @Override
    public void saveToken(T object, TokenDTO tokenDTO) {
        this.tokensMap.put(tokenDTO,object);
        this.ObjectMap.put(object,tokenDTO);
    }

    @Override
    public synchronized void deleteToken(T object) {
        this.tokensMap.remove(object);
    }

    public void clearExpiredTokens() {

        Set<Map.Entry<TokenDTO, T>> entries = this.tokensMap.entrySet();
        List<TokenDTO> tokenToDelete = new LinkedList<>();

        for (Map.Entry<TokenDTO, T> entry : entries) {

            TokenDTO token = entry.getKey();
            if (token.getDateOfExpire().isAfter(Instant.now())) {
                tokenToDelete.add(entry.getKey());
            }
        }

        for (TokenDTO tokenDTO : tokenToDelete) {
            T remove = this.tokensMap.remove(tokenDTO);
            this.ObjectMap.remove(remove);
        }


    }
}
