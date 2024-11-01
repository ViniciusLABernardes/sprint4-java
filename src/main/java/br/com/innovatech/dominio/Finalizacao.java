package br.com.innovatech.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Finalizacao  {
	@JsonProperty
	private String numeroCartao;
	@JsonProperty
	private int cvv;
	@JsonProperty
	private String validade;
		public Finalizacao(){

		}
		public Finalizacao(String numeroCartao, int cvv, String validade){
			this.numeroCartao = numeroCartao;
			this.cvv = cvv;
			this.validade = validade;	
		}
	public void realizarPag(Cartao dadosCartao) {
		if(dadosCartao.getNumero().equals(this.numeroCartao) && dadosCartao.getCVV() == this.cvv && dadosCartao.getValidade().equals(this.validade)) {
			System.out.println("pagamento realizado com sucesso!");
		}else {
			System.out.println("cartão não coincide com o cartão cadastrado");
		}
	}
}
