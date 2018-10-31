package com.WebSocketRpc.application.services;

import com.WebSocketRpc.api.ProcedureDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ProcedureDTOConverter<LT> {

    private ObjectMapper mapper;

    public ProcedureDTOConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        this.mapper = mapper;
    }

    public String toJsonString(ProcedureDTO procedure) throws JsonProcessingException {
        return this.mapper.writeValueAsString(procedure);
    }
    public ProcedureDTO<LT,?> toProcedureDTO(String json ) throws IOException {

        String hello = " dfddd";

        Class<? extends String> aClass = hello.getClass();

        // TODO: 31.10.2018 Error during parsing dto
        return this.mapper.readValue(json,ProcedureDTO.class);

    }

}
