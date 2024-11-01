package br.com.innovatech.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogarClienteRequest {
    @JsonProperty
    private String login;
    @JsonProperty
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
