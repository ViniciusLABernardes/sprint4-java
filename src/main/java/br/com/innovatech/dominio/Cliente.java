package br.com.innovatech.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Cliente implements Verificador {
	@JsonProperty
	private String nome;
	@JsonProperty
	private String cpf;
	@JsonProperty
	private int idade;
	@JsonProperty
	private String email;
	@JsonProperty
	private String login;
	@JsonProperty
	private String senha;
	@JsonProperty
	private String telefone;
	@JsonProperty
	private Cartao cartao;
	@JsonProperty
	private Endereco endereco;
	public Cliente(){

	}
	public Cliente(String nome, String cpf, int idade, String senha,String email, String login, String telefone){
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.senha = senha;
		this.email = email;
		this.login = login;
		this.telefone = telefone;
	}

	public Cliente(String login) {
	}

	public void solicitarReparo(ArrayList<Problema> problemas) {
	}
	public String getNome(){
		return this.nome;
	}
	public String setNome(String novoNome) {
		return this.nome = novoNome;
	}

	public String getSenha() {
		return this.senha;
	}

	public String setSenha(String novaSenha) {

		return this.senha = novaSenha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean login(String login, String senha){
		if(this.login.equals(login) && this.senha.equals(senha)){
			System.out.println("Logado");
			return true;
		}else{
			System.out.println("Credenciais incorretas");
			return false;
		}

	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEmail() {
		return email;
	}

	public String setEmail(String email) {
		return this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getLogin(){
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getTelefone() {
		return telefone;
	}
	public String setTelefone(String telefone){
		return this.telefone = telefone;
	}
	public boolean equals(Object obj){
		if(obj == null)return false;
		if(!(obj instanceof Cliente)) return false;
		Cliente outro = (Cliente) obj;
		if((cpf.equals(outro.cpf)) && (email.equals(outro.email)) && (login.equals(outro.login))
		)return true;
		return false;
	}

	@Override
	public String toString(){
		return "nome do usuario: " + nome + ", cpf: " + cpf + ", email: " + email;
	}
}
