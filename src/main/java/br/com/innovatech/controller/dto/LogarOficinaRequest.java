package br.com.innovatech.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogarOficinaRequest {
    @JsonProperty
    private String loginOficina;
    @JsonProperty
    private String senha;

    public String getLoginOficina() {
        return loginOficina;
    }

    public void setLoginOficina(String loginOficina) {
        this.loginOficina = loginOficina;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
