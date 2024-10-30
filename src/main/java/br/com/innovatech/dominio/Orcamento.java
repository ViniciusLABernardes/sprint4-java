package br.com.innovatech.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Orcamento {
	@JsonProperty
	private double valor;
	@JsonProperty
	private String descricao;
	public Orcamento(){

	}
	public Orcamento(double valor, String descricao){
		this.valor = valor;
		this.descricao = descricao;
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
}
