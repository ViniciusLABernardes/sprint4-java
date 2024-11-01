package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.CarroCliente;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AttCarroClienteRequest {
    @JsonProperty
    private String modelo;
    @JsonProperty
    private String marca;
    @JsonProperty
    private int ano;
    @JsonProperty
    private CarroCliente carroCliente;
    @JsonProperty
    private String login;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public CarroCliente getCarroCliente() {
        return carroCliente;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }


    public int getAno() {
        return ano;
    }

}
