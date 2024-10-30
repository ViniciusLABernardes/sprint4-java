package br.com.innovatech.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Peca {
	@JsonProperty
	private String nomePeca;
	@JsonProperty
	private double precoPeca;
	@JsonProperty
	private int quantidade;

	public Peca(){

	}
	public Peca(String nomePeca, double precoPeca, int quantidade){
		this.nomePeca = nomePeca;
		this.precoPeca = precoPeca;
		this.quantidade = quantidade;
	}

	public double alterarPrecoPeca(double precoPeca) {
		return this.precoPeca = precoPeca;
	}
	public int quantidade(int quantidade) {
		return this.quantidade = quantidade;
	}
}
