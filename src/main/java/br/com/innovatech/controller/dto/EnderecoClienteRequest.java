package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.Endereco;

public class EnderecoClienteRequest {

    private Endereco endereco;
    private String login;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
