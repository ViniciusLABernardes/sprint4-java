package br.com.innovatech.dominio;

import java.util.ArrayList;

public interface RepositorioCarro {

    void inserirCarro(String login,CarroCliente carroCliente);
    void fecharConexao();
    ArrayList<CarroCliente> lerCarrosCliente(String login);
    void atualizarInformacaoCarro(String modelo, String marca, int ano, CarroCliente carroCliente, String login);
    void deletarCarro(String modeloCarro, String login);
    void inserirProblema(Problema problema, String login);
}
