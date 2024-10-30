package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.Cartao;

public class RealizarPagClienteRequest {
    private Cartao cartao;
    private String login;
    private String modeloCarro;

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }
}
