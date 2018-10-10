package com.chat.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestWebSocket {

    @RequestMapping("/chat")
    public String chat(){
        return "Client.html";
    }
}
