package com.supertask.chat.api.rpc;

import com.supertask.chat.vendors.LoggerServiceModule.DbLogger;
import com.supertask.chat.vendors.AuthenticateServiceModule.domain.services.UserAuthenticate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/rpc")
public class LoginRPC {


    private UserAuthenticate userAuthenticate;
    private DbLogger dbLogger;

    public LoginRPC(UserAuthenticate userAuthenticate, DbLogger dbLogger) {
        this.userAuthenticate = userAuthenticate;
        this.dbLogger = dbLogger;
    }

    @ResponseBody
    @CrossOrigin
    @PostMapping("/login")

    public boolean validateUser (HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest, @RequestBody UsetToLogin usetToLogin){

        userAuthenticate.authenticate(usetToLogin.getNick(),usetToLogin.getPassword());


        return true;


    }

}
