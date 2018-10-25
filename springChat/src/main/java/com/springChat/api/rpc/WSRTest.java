package com.springChat.api.rpc;

import com.WebSocketRpc.api.WSR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WSRTest {

    private WSR<String,String,Integer> wsr;


    @Autowired
    public WSRTest(WSR wsr) {
        this.wsr = wsr;

        wsr.addProcedure("RMTEST",String.class,(data, session) -> {

            System.out.println("WSR: "+data);
            session.executeRemoteProcedure("TEST",String.class,"Message from server");
        });


    }
}
