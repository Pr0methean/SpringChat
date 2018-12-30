package com.springChat.api.rpc;

import com.springChat.domain.ports.UserAuthenticatorPort.UserAuthenticator;
import com.springChat.infrastructure.DbLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/rpc")
public class LoginRPC {


    private UserAuthenticator userAuthenticate;
    private DbLogger dbLogger;

    public LoginRPC(UserAuthenticator userAuthenticate, DbLogger dbLogger) {
        this.userAuthenticate = userAuthenticate;
        this.dbLogger = dbLogger;
    }

    @ResponseBody
    @CrossOrigin
    @PostMapping("/login")

    public boolean validateUser (HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest, @RequestBody com.springChat.api.rpc.UsetToLogin usetToLogin){

        //userAuthenticate.authenticate(usetToLogin.getNick(),usetToLogin.getPassword());


        return true;


    }

}
