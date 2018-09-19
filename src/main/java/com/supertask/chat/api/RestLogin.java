package com.supertask.chat.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

public class RestLogin {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public String Login(){
        return "Login";
    }

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "index";

    }

}
