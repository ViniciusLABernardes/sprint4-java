package br.com.innovatech.service;

import br.com.innovatech.dominio.CarroCliente;

import br.com.innovatech.dominio.Problema;
import br.com.innovatech.dominio.RepositorioCarro;


import java.util.ArrayList;

public class CarroClienteService {

    private RepositorioCarro repositorioCarros;

    public CarroClienteService(RepositorioCarro repositorioCarro){
        this.repositorioCarros = repositorioCarro;
    }


    public void inserirCarro(String login, CarroCliente carroCliente){
        repositorioCarros.inserirCarro(login,carroCliente);
        repositorioCarros.fecharConexao();
    }

    public ArrayList<CarroCliente> lerCarrosCliente(String login){
        ArrayList<CarroCliente> carros = repositorioCarros.lerCarrosCliente(login);
        repositorioCarros.fecharConexao();
        return carros;
    }
    public void atualizarInformacaoCarro(String modelo, String marca, int ano, CarroCliente carroCliente, String login){
        repositorioCarros.atualizarInformacaoCarro(modelo, marca, ano, carroCliente, login);
        repositorioCarros.fecharConexao();
    }
    public void deletarCarro(String modeloCarro, String login){
        repositorioCarros.deletarCarro(modeloCarro, login);
        repositorioCarros.fecharConexao();
    }
    public void inserirProblema(Problema problema, String login){
        repositorioCarros.inserirProblema(problema,login);
        repositorioCarros.fecharConexao();
    }
}
