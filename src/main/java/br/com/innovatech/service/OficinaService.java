package br.com.innovatech.service;

import br.com.innovatech.dominio.*;

import java.util.ArrayList;

public class OficinaService {
    private RepositorioOficina repositorioOficina;

    public OficinaService(RepositorioOficina repositorioOficina){
        this.repositorioOficina = repositorioOficina;
    }

    public void inserirServico(Servico servico, String loginOficina, String modeloCarro, String descricaoProblema){
        repositorioOficina.inserirServico(servico,loginOficina,modeloCarro,descricaoProblema);
        repositorioOficina.fecharConexao();

    }
    public void inserirOficina(Oficina oficina){
        repositorioOficina.inserirOficina(oficina);
        repositorioOficina.fecharConexao();
    }
    public void inserirPeca(Peca peca, String modeloCarro,String descricaoProblema){
        repositorioOficina.inserirPeca(peca, modeloCarro,descricaoProblema);
        repositorioOficina.fecharConexao();
    }
    public ArrayList<Oficina> lerOficinas(){
        ArrayList<Oficina> oficinas = repositorioOficina.lerOficinas();
        repositorioOficina.fecharConexao();
        return oficinas;
    }
    public ArrayList<Problema> lerProblemas(){
        ArrayList<Problema> problemas = repositorioOficina.lerProblemas();
        repositorioOficina.fecharConexao();
        return problemas;
    }
    public void inserirOrcamento(Orcamento orcamento, String modeloCarro, String descricaoProblema){
        repositorioOficina.inserirOrcamento(orcamento, modeloCarro, descricaoProblema);
        repositorioOficina.fecharConexao();
    }

    public boolean logarOficina(String loginOficina,String senha){
        if(repositorioOficina.logarOficina(loginOficina, senha)){
            repositorioOficina.fecharConexao();
            return true;
        }else{
            repositorioOficina .fecharConexao();
            return false;
        }
    }
}
