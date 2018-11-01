package com.WebSocketRpc.api;

public class NewProcedureDTO {
    private String typeJson;
    private String dataJson;

    public NewProcedureDTO() {
    }

    public String getTypeJson() {
        return typeJson;
    }

    public void setTypeJson(String typeJson) {
        this.typeJson = typeJson;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }
}
