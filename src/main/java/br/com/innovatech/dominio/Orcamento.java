package br.com.innovatech.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.ArrayList;

public class Orcamento {

	@JsonProperty
	private double valor;
	@JsonProperty
	private String descricao;
	@JsonProperty
	private String dataOrcamento;
	public Orcamento(){

	}
	public Orcamento(double valor, String descricao, String dataOrcamento){

		this.valor = valor;
		this.descricao = descricao;
		this.dataOrcamento = dataOrcamento;
	}
	public Orcamento(double valor, String descricao, Date dataOrcamento){
		this.valor = valor;
		this.descricao = descricao;
		this.dataOrcamento = String.valueOf(dataOrcamento);
	}

	public Orcamento(String descricao, Date dataOrcamento){
		this.descricao = descricao;
		this.dataOrcamento = String.valueOf(dataOrcamento);
	}

	public double getValor() {
		return this.valor;
	}



	public double calculoValor(ArrayList<Problema> qntProblema) {
		double valor = 0;
		for(Problema problemas : qntProblema) {
			valor += problemas.verificarPrecoTotal();
		}
		return this.valor = valor;
	}

	public String exibirValor() {
		return "o valor total do reparo ficou em: " + this.valor;
	}

	public String getDescricao() {
		return descricao;
	}


	public String getDataOrcamento() {
		return dataOrcamento;
	}

}



