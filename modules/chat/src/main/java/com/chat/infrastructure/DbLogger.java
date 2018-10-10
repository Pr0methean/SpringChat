package com.chat.infrastructure;

import com.chat.domain.model.ServerLog;
import com.chat.domain.ports.LogReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbLogger {

    private LogReposytory logReposytory;

    @Autowired
    public DbLogger(LogReposytory logReposytory) {
        this.logReposytory = logReposytory;
    }

    public void log(ServerLog serverLog){

        logReposytory.saveLog(serverLog);
    }
}
