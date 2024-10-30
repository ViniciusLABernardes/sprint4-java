package br.com.innovatech.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Endereco {
	@JsonProperty
	private String logradouro;
	@JsonProperty
	private int numero;
	@JsonProperty
	private String complemento;
	@JsonProperty
	private String cep;
	@JsonProperty
	private String cidade;
	@JsonProperty
	private String uf;
	public Endereco(){

	}
		public Endereco(String logradouro,int numero, String complemento, 
String cep, String cidade, String uf) {
			this.logradouro = logradouro;
			this.numero = numero;
			this.complemento = complemento;
			this.cep = cep;
			this.cidade = cidade;
			this.uf = uf;
		}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
