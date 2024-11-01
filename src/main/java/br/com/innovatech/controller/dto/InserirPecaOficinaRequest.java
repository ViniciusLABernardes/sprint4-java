package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.Peca;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InserirPecaOficinaRequest {
    @JsonProperty
    private Peca peca;
    @JsonProperty
    private String modeloCarro;
    @JsonProperty
    private String descricaoProblema;

    public Peca getPeca() {
        return peca;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

}
