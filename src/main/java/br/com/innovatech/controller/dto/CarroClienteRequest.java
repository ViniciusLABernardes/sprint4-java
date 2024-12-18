package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.CarroCliente;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CarroClienteRequest {
    @JsonProperty
    private String login;
    @JsonProperty
    private CarroCliente carroCliente;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public CarroCliente getCarroCliente() {
        return carroCliente;
    }

}