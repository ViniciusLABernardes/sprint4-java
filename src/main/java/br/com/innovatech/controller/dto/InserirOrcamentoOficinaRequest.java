package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.Orcamento;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InserirOrcamentoOficinaRequest {
    @JsonProperty
    private Orcamento orcamento;
    @JsonProperty
    private String modeloCarro;
    @JsonProperty
    private String descricaoProblema;

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }
}
