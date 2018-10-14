package com.chat.api.pages;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError() {
        return "404 Page Not Found";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
