package br.com.innovatech.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class Oficina implements Verificador{
    @JsonProperty
    private String nomeOficina;
    @JsonProperty
    private String cnpj;
    @JsonProperty
    private String horarioFuncionamento;
    @JsonProperty
    private String dataAbertura;
    @JsonProperty
    private String loginOficina;
    @JsonProperty
    private String senha;

    public Oficina(String nomeOficina, String cnpj, String horarioFuncionamento, String dataAbertura,String loginOficina, String senha){
        this.nomeOficina = nomeOficina;
        this.cnpj = cnpj;
        this.horarioFuncionamento = horarioFuncionamento;
        this.dataAbertura = dataAbertura;
        this.loginOficina = loginOficina;
        this.senha = senha;

    }
    public Oficina(){

    }

    public Oficina(String nomeOficina, String cnpj, String horarioFuncionamento, Date dataAbertura) {
        this.nomeOficina = nomeOficina;
        this.cnpj = cnpj;
        this.horarioFuncionamento = horarioFuncionamento;
        this.dataAbertura = String.valueOf(dataAbertura);
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

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getLoginOficina() {
        return loginOficina;
    }

    public void setLoginOficina(String loginOficina) {
        this.loginOficina = loginOficina;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public boolean login(String login, String senha) {
        if(this.loginOficina.equals(login) && this.senha.equals(senha)){
            System.out.println("Logado");
            return true;
        }else{
            System.out.println("Credenciais incorretas");
            return false;
        }
    }
}
