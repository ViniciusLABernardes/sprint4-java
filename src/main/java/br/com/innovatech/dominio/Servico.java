package br.com.innovatech.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Servico {
    @JsonProperty
    private String categoriaServico;
    @JsonProperty
    private double precoServico;

    public Servico(){

    }
    public Servico(String categoriaServico, double precoServico){
        this.categoriaServico = categoriaServico;
        this.precoServico = precoServico;
    }
    public double getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(double precoServico) {
        this.precoServico = precoServico;
    }

    public String getCategoriaServico() {
        return categoriaServico;
    }

    public void setCategoriaServico(String categoriaServico) {
        this.categoriaServico = categoriaServico;
    }
}
