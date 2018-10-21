package com.WebSocketRpc.infrastructure;

import com.WebSocketRpc.domain.model.Session;
import com.WebSocketRpc.domain.ports.SessionRepository;

public class SessionRepositoryInMomory<T,I>  implements SessionRepository<T,I>  {
    @Override
    public void addSession(Session<T, I> session) {

    }

    @Override
    public void removeSession(I Id) {

    }

    @Override
    public Session<T, I> getSession(I Id) {
        return null;
    }
}
