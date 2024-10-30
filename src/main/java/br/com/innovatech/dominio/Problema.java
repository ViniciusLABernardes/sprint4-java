package br.com.innovatech.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Problema {
	@JsonProperty
	private String descricaoProblema;
	@JsonProperty
	private int quantidadeProblema;
	@JsonProperty
	Finalizacao finalizacao;
	public Problema(String descricaoProblema, int quantidadeProblema){
		this.descricaoProblema = descricaoProblema;

		this.quantidadeProblema = quantidadeProblema;
	}
	public Problema(){

	}
	public String getDescricaoProblema(){
		return this.descricaoProblema;
	}


	public double verificarPrecoTotal() {
		double valorDoProblema = 0;

		if(this.descricaoProblema.equals("problema no motor")) {
			System.out.println("O problema encontra-se no sistema de resfriamento do motor");
			valorDoProblema += 1000;
		}else if(this.descricaoProblema.equals("problema no câmbio")) {
			System.out.println("O problema é um desgaste na haste seletora");
			valorDoProblema += 200;
		}else if(this.descricaoProblema.equals("problema no ar condicionado")) {
			System.out.println("O problema encontra-se no compressor");
			valorDoProblema += 400;
		}
		return valorDoProblema;
	}


	public int getQuantidadeProblema() {
		return quantidadeProblema;
	}

	public void setQuantidadeProblema(int quantidadeProblema) {
		this.quantidadeProblema = quantidadeProblema;
	}
}
