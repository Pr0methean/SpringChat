package com.supertask.chat.domain.ports;

import com.supertask.chat.domain.model.ServerLog;

public interface LogReposytory {

    public void saveLog(ServerLog serverLog);
}
