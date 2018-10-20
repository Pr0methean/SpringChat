package com.springChat.application.services.socket.model.procedure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Proc;

import java.io.IOException;

public class ProcedureConverter {

    private ObjectMapper mapper;

    public ProcedureConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        this.mapper = mapper;
    }

    public String toJsonString(Procedure procedure) throws JsonProcessingException {
        return this.mapper.writeValueAsString(procedure);
    }
    public Procedure toObject(String json) throws IOException {
        return this.mapper.readValue(json,Procedure.class);
    }
}
