package com.springChat.infrastructure;

import com.springChat.domain.model.ServerLog;
import com.springChat.domain.ports.LogReposytory;
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
        new Thread(()->{
            logReposytory.saveLog(serverLog);
        }).start();
    }
}
