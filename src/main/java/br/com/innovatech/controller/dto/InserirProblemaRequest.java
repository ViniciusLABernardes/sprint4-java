package br.com.innovatech.controller.dto;

import br.com.innovatech.dominio.Problema;

public class InserirProblemaRequest {
    private Problema problema;
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }
}
