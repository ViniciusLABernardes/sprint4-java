package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.CarroCliente;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeletarCarroRequest {
    @JsonProperty
    private CarroCliente carroCliente;
    @JsonProperty
    private String login;


    public CarroCliente getCarroCliente() {
        return carroCliente;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
