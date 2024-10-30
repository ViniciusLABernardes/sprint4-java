package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.CarroCliente;

public class AttCarroClienteRequest {
    private String modelo;
    private String marca;
    private int ano;
    private CarroCliente carroCliente;
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

    public void setCarroCliente(CarroCliente carroCliente) {
        this.carroCliente = carroCliente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
