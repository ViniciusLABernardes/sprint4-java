package br.com.innovatech.service;

import br.com.innovatech.dominio.Cartao;
import br.com.innovatech.dominio.Cliente;
import br.com.innovatech.dominio.Endereco;
import br.com.innovatech.dominio.RepositorioCliente;

public class ClienteService {

    private RepositorioCliente repositorioCliente;

    public ClienteService(RepositorioCliente repositorioCliente){
        this.repositorioCliente = repositorioCliente;
    }

    public void inserirCliente(Cliente cliente){
        repositorioCliente.inserirCliente(cliente);
        repositorioCliente.fecharConexao();
    }
    public boolean logarCliente(Cliente cliente){
        if(repositorioCliente.logarCliente(cliente)){
            repositorioCliente.fecharConexao();
            return true;
        }else{
            repositorioCliente.fecharConexao();
            return false;
        }
    }
    public void inserirEndereco(Endereco endereco, String login){
        repositorioCliente.inserirEndereco(endereco,login);
        repositorioCliente.fecharConexao();
    }
    public void realizarPagamento(Cartao cartao, String login, String modeloCarro){
        repositorioCliente.realizarPagamento(cartao,login,modeloCarro);
        repositorioCliente.fecharConexao();
    }


}
