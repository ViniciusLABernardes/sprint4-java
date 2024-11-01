package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.Endereco;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EnderecoClienteRequest {
    @JsonProperty
    private Endereco endereco;
    @JsonProperty
    private String login;

    public Endereco getEndereco() {
        return endereco;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
