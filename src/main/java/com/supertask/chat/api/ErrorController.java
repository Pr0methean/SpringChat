package com.supertask.chat.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String index(){
        return "Error page not found";

    }
}
