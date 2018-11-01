package com.WebSocketRpc.application.services;

import com.WebSocketRpc.api.NewProcedureDTO;
import com.WebSocketRpc.api.ProcedureDTO;
import com.WebSocketRpc.domain.model.Procedure;
import com.WebSocketRpc.domain.ports.ProcedureRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ProcedureDTOConverter<LT> {

    private ObjectMapper mapper;
    private ProcedureRepository<LT> procedureRepository;

    public ProcedureDTOConverter(ProcedureRepository<LT> procedureRepository) {
        this.procedureRepository = procedureRepository;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        this.mapper = mapper;
    }

    public String toJsonString(ProcedureDTO procedure) throws JsonProcessingException {

        final Object data = procedure.getData();
        final Object type = procedure.getType();
        final String dataJson = this.mapper.writeValueAsString(data);
        final String typeJson = this.mapper.writeValueAsString(type);
        final NewProcedureDTO newProcedureDTO = new NewProcedureDTO();
        newProcedureDTO.setDataJson(dataJson);
        newProcedureDTO.setTypeJson(typeJson);
        return this.mapper.writeValueAsString(newProcedureDTO);
    }
    public ProcedureDTO<LT,?> toProcedureDTO(String json ) throws IOException {

        final NewProcedureDTO newProcedureDTO = this.mapper.readValue(json, NewProcedureDTO.class);

        final String typeProcedureJson = newProcedureDTO.getTypeJson();
        final String dataProcedureJson = newProcedureDTO.getDataJson();

        final LT procedureType = (LT) this.mapper.readValue(typeProcedureJson, this.procedureRepository.getProcedureTypeClass());
        final Procedure<LT> procedure = this.procedureRepository.getProcedure(procedureType);

        final Object data = this.mapper.readValue(dataProcedureJson, procedure.getProcedureDataType());

        final ProcedureDTO<LT, Object> procedureDTOToRetur = new ProcedureDTO<>();
        procedureDTOToRetur.setData(data);
        procedureDTOToRetur.setType(procedureType);

        // TODO: 31.10.2018 Error during parsing dto
        return procedureDTOToRetur;

    }

}
