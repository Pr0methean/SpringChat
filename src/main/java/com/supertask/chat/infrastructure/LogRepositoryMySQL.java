package com.supertask.chat.infrastructure;

import com.supertask.chat.domain.model.ServerLog;
import com.supertask.chat.domain.ports.LogReposytory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class LogRepositoryMySQL implements LogReposytory {
    @Override
    public void saveLog(ServerLog serverLog) {
        //TODO: do zakonczenia
    }

    @Override
    public List<ServerLog> listLogs() {
        return null;
    }
}
