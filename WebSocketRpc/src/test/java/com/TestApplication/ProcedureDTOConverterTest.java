package com.TestApplication;


import com.WebSocketRpc.api.ProcedureDTO;
import com.WebSocketRpc.application.services.ProcedureDTOConverter;
import com.WebSocketRpc.domain.model.Procedure;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;


import java.io.IOException;

public class ProcedureDTOConverterTest<LT> {

    private ProcedureDTOConverter<LT> procedureDTOConverter;


    public ProcedureDTOConverterTest() {
        this.procedureDTOConverter = new ProcedureDTOConverter<LT>();
    }


    @Test
    public void shouldConvertJSONStringToProcedureDTO() {

        try {
            //given

            String jsonString = "{\"type\":\"login\",\"data\":\"frank\"}";


            //when
            ProcedureDTO<LT, ?> ltProcedureDTO = procedureDTOConverter.toProcedureDTO(jsonString);


            //then
            Assert.assertEquals(ltProcedureDTO.getType(), "login");
            Assert.assertEquals(ltProcedureDTO.getData(), "frank");

            //then
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void shouldConvertProcedureDTOtoJsonString() {
        try {
            //given

            ProcedureDTO procedureDTO = new ProcedureDTO("login", "frank");
            String result = "{\"type\":\"login\",\"data\":\"frank\"}";

            //when
            String jsonString = procedureDTOConverter.toJsonString(procedureDTO);

            //then
            Assert.assertEquals(result,jsonString);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
