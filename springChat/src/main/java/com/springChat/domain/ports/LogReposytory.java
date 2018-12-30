package com.springChat.domain.ports;


import com.springChat.domain.model.ServerLog;

import java.util.List;

public interface LogReposytory {

    void saveLog(ServerLog serverLog);
    List<ServerLog> listLogsOnDate(String dateTime);
}
