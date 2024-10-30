package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.CarroCliente;

public class DeletarCarroRequest {
    private CarroCliente carroCliente;
    private String login;


    public CarroCliente getCarroCliente() {
        return carroCliente;
    }

    public void setCarroCliente(CarroCliente carroCliente) {
        this.carroCliente = carroCliente;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
