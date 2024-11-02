package br.com.innovatech.dominio;

import java.util.ArrayList;

public interface RepositorioOficina {

    void inserirOficina(Oficina oficina);
    boolean logarOficina(String loginOficina, String senha);
    ArrayList<Oficina> lerOficinas();
    ArrayList<Problema> lerProblemas();
    void fecharConexao();
    void inserirServico(Servico servico, String loginOficina,String modeloCarro, String descricaoProblema);
    void inserirPeca(Peca peca, String modeloCarro, String descricaoProblema);
    void inserirOrcamento(Orcamento orcamento,String modeloCarro, String descricaoProblema);
}
