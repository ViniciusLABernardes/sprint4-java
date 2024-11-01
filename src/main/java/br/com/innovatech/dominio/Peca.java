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

	public String getNomePeca() {
		return nomePeca;
	}

	public void setNomePeca(String nomePeca) {
		this.nomePeca = nomePeca;
	}

	public double getPrecoPeca() {
		return precoPeca;
	}

	public void setPrecoPeca(double precoPeca) {
		this.precoPeca = precoPeca;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
