package com.supertask.chat.vendors.LoggerServiceModule;

import com.supertask.chat.domain.model.ServerLog;
import com.supertask.chat.domain.ports.LogReposytory;
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
