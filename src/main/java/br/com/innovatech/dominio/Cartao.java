package br.com.innovatech.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class Cartao {
	@JsonProperty
	private String numero;
	@JsonProperty
	private int cvv;
	@JsonProperty
	private String validade;

	public Cartao(){

	}

		public Cartao(String numero, int cvv, String validade) {
			this.numero = numero;
			this.cvv = cvv;
			this.validade = validade;
		}
		public String getNumero(){
			return this.numero;
		}
		public String getValidade(){
			return this.validade;
		}
		public int getCVV(){
			return this.cvv;
		}
}
