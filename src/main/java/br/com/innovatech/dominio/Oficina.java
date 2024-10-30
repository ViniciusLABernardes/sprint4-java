package br.com.innovatech.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Oficina {
    @JsonProperty
    private String nomeOficina;
    @JsonProperty
    private String cnpj;
    @JsonProperty
    private String horarioFuncionamento;
    @JsonProperty
    private Date dataAbertura;

    public Oficina(String nomeOficina, String cnpj, String horarioFuncionamento, Date dataAbertura){
        this.nomeOficina = nomeOficina;
        this.cnpj = cnpj;
        this.horarioFuncionamento = horarioFuncionamento;
        this.dataAbertura = dataAbertura;

    }
    public Oficina(){

    }
    public String getNomeOficina() {
        return nomeOficina;
    }

    public void setNomeOficina(String nomeOficina) {
        this.nomeOficina = nomeOficina;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

}
