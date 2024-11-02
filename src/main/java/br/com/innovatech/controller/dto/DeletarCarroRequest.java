package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.CarroCliente;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeletarCarroRequest {
    @JsonProperty
    private String modeloCarro;
    @JsonProperty
    private String login;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro){
        this.modeloCarro = modeloCarro;
    }
}
