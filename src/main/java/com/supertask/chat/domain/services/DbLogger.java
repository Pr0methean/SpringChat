package com.supertask.chat.domain.services;

import com.supertask.chat.domain.model.ServerLog;
import com.supertask.chat.domain.ports.LogReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbLogger {

    @Autowired
    private LogReposytory logReposytory;

    public void log(ServerLog serverLog){

        logReposytory.saveLog(serverLog);
    }
}
