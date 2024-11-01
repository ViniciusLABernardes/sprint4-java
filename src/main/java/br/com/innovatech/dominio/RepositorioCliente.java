package br.com.innovatech.dominio;

public interface RepositorioCliente {

    void inserirCliente(Cliente cliente);
    boolean logarCliente(String login, String senha);
    void fecharConexao();
    void inserirEndereco(Endereco endereco, String login);
    void realizarPagamento(Cartao cartao, String login, String modeloCarro, String descricaoProblema);

}
