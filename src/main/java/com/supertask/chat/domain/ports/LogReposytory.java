package com.supertask.chat.domain.ports;

import com.supertask.chat.domain.model.ServerLog;

import java.util.List;

public interface LogReposytory {

    void saveLog(ServerLog serverLog);
    List<ServerLog> listLogsOnDate(String dateTime);
}
