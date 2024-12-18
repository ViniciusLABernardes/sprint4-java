package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.Cartao;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RealizarPagClienteRequest {
    @JsonProperty
    private Cartao cartao;
    @JsonProperty
    private String login;
    @JsonProperty
    private String modeloCarro;
    @JsonProperty
    private String descricaoProblema;

    public Cartao getCartao() {
        return cartao;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
