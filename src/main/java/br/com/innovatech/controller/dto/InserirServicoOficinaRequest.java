package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.Servico;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InserirServicoOficinaRequest {
    @JsonProperty
    private Servico servico;
    @JsonProperty
    private String loginOficina;
    @JsonProperty
    private String modeloCarro;
    @JsonProperty
    private String descricaoProblema;

    public String getLoginOficina() {
        return loginOficina;
    }


    public Servico getServico() {
        return servico;
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
