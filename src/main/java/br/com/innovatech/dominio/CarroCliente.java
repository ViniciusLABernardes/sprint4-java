package br.com.innovatech.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarroCliente {

	@JsonProperty
	private String modeloCarro;
	@JsonProperty
	private String marca;
	@JsonProperty
	private int anoFabricacao;
	public CarroCliente(){

	}
	public CarroCliente(String modeloCarro, String marca, int anoFabricacao){
		this.modeloCarro = modeloCarro;
		this.marca = marca;
		this.anoFabricacao = anoFabricacao;
	}
	public String dadosCarro() {
		return this.modeloCarro +"\n" +this.marca +"\n" + this.anoFabricacao;
	}

	public boolean disponibilidade() {
		if (this.anoFabricacao <= 2006) {
			System.out.println("carro indisponivel ao conserto");
			return false;
		}
		else 
			System.out.println("carro disponivel ao conserto");
		return true;
	}

	public String getModeloCarro(){
		return this.modeloCarro;
	}
	public String setModeloCarro(String modeloCarro){
		return this.modeloCarro = modeloCarro;
	}

	public String getmarca(){
		return this.marca;
	}
	public String setMarca(String marca){
		return this.marca = marca;
	}

	public int getAno(){
		return this.anoFabricacao;
	}
	public int setAno(int ano){
		return this.anoFabricacao = ano;
	}

	public boolean equals(Object obj){
		if(obj == null)return false;
		if(!(obj instanceof CarroCliente)) return false;
		CarroCliente outro = (CarroCliente) obj;
		if((modeloCarro.equals(outro.modeloCarro)) && (marca.equals(outro.marca)) && (anoFabricacao == anoFabricacao))return true;
		return false;
	}
	@Override
	public String toString(){
		return "modelo do carro: " + modeloCarro + ", marca: " + marca + ", ano fabricação: " + anoFabricacao + "\n";
	}
}
