package com.springChat.api.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketClientController {

    @RequestMapping("/chat")
    public String chat(){
        return "Client.html";
    }
}
