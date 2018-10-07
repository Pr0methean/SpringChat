package com.supertask.chat.api.restLogger;


import com.supertask.chat.domain.model.ServerLog;
import com.supertask.chat.domain.model.dto.Link;
import com.supertask.chat.domain.ports.LogReposytory;
import com.supertask.chat.vendors.LoggerServiceModule.DbLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RestLogger {

    @Autowired
    private LogReposytory logReposytory;

    @Autowired
    private DbLogger dbLogger;


    @ResponseBody
    @CrossOrigin
    @GetMapping("/logger/{dateTime}")
    public List<ServerLogDTO> getServerLogsByDate(HttpServletRequest request,HttpServletResponse response ,@PathVariable("dateTime") String dateTime) {

        try {
            if (dateTime.contains("T")) {
                dateTime = dateTime.replace("T", " ");
            }
            List<ServerLogDTO> listServerLogDTO = new ArrayList<>();
            List<ServerLog> listServerLogList = new ArrayList<>();

            listServerLogList = logReposytory.listLogsOnDate(dateTime);
            for (ServerLog serverLog : listServerLogList) {
                ServerLogDTO serverLogDTO = new ServerLogDTO(serverLog);
                serverLogDTO.addLink(new Link("self", "/messages/" + serverLog.getId()));
                listServerLogDTO.add(serverLogDTO);
            }
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            response.setStatus(201);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type", "application/json");
            return listServerLogDTO;
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }


        return null;
    }

}
