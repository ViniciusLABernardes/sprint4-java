package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.Problema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InserirProblemaRequest {
    @JsonProperty
    private Problema problema;
    @JsonProperty
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Problema getProblema() {
        return problema;
    }

}
