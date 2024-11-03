package br.com.innovatech.conn;

import br.com.innovatech.dominio.*;

import java.sql.*;
import java.util.ArrayList;

public class OficinaDAO implements RepositorioOficina {
    private Connection conexao;

    public OficinaDAO(){
        conexao = new ConnectionFactory().pegarConexao();
    }


    public void inserirOficina(Oficina oficina) {
        String comandoInsercao = "INSERT INTO TB_OFICINA(nome_oficina,cnpj,horario_funcionamento,data_abertura) VALUES (?,?,?,?)";
        String comandoInsercao2 = "INSERT INTO TB_LOGIN_OFICINA(id_oficina,login_oficina, senha) VALUES (?,?,?)";


        try {
            PreparedStatement preparandoInsercao = conexao.prepareStatement(comandoInsercao, new String[]{"id_oficina"});
            preparandoInsercao.setString(1, oficina.getNomeOficina());
            preparandoInsercao.setString(2, oficina.getCnpj() );
            preparandoInsercao.setString(3, oficina.getHorarioFuncionamento());
            preparandoInsercao.setString(4, oficina.getDataAbertura());

            preparandoInsercao.executeUpdate();

            ResultSet pegandoId = preparandoInsercao.getGeneratedKeys();

            if(pegandoId.next()){
                int idOficina = pegandoId.getInt(1);
                PreparedStatement preparandoInsercao2 = null;
                PreparedStatement preparandoInsercao3 = null;
                try {
                    preparandoInsercao2 = conexao.prepareStatement(comandoInsercao2);

                    preparandoInsercao2.setInt(1, idOficina);
                    preparandoInsercao2.setString(2, oficina.getLoginOficina());
                    preparandoInsercao2.setString(3, oficina.getSenha());

                    preparandoInsercao2.executeUpdate();


                    System.out.println("Cadastro de oficina realizado com sucesso!");
                }finally {
                    pegandoId.close();
                    if (preparandoInsercao2 != null) preparandoInsercao2.close();

                }
            }

            preparandoInsercao.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Não foi possivel inserir os dados" + e);
        }

    }



    public ArrayList<Oficina> lerOficinas() {
        String comandoLeituraOficinas = "SELECT * FROM TB_OFICINA";
        ArrayList<Oficina> oficinas = new ArrayList<>();
        try{
            PreparedStatement leituraOficinas = conexao.prepareStatement(comandoLeituraOficinas);
            ResultSet resultSet = leituraOficinas.executeQuery();
            while(resultSet.next()){
                Oficina oficina = new Oficina(
                resultSet.getString("nome_oficina"),
                resultSet.getString("cnpj"),
                resultSet.getString("horario_funcionamento"),
                resultSet.getDate("data_abertura"));
                oficinas.add(oficina);
            }
            resultSet.close();
            leituraOficinas.close();
            return oficinas;

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Problema> lerProblemas() {
        String comandoLeituraProblemas = "SELECT c.modelo_carro,p.descricao_problema,p.quantidade_problema FROM TB_CARRO c INNER JOIN TB_PROBLEMA p ON c.id_carro = p.id_carro";

        ArrayList<Problema> problemas = new ArrayList<>();
        try{

            PreparedStatement leituraProblemas = conexao.prepareStatement(comandoLeituraProblemas);
            ResultSet resultSet = leituraProblemas.executeQuery();
            while(resultSet.next()){
                Problema problema = new Problema(
                        resultSet.getString("modelo_carro"),
                        resultSet.getString("descricao_problema"),
                        resultSet.getInt("quantidade_problema")

                );

                problemas.add(problema);
            }
            resultSet.close();
            leituraProblemas.close();
            return problemas;


        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void fecharConexao() {
        try{
            conexao.close();
        }catch (SQLException e){
            System.out.println("Não foi possivel fechar a conexão com o banco: " + e.getMessage());
        }
    }


    public void inserirServico(Servico servico, String loginOficina, String modeloCarro, String descricaoProblema) {

        String comandoInsercaoServico = "INSERT INTO TB_SERVICO(categoria_servico,id_oficina,id_problema,preco_servico) " +
                "VALUES (?,?,?,?)";
        String comandoBuscaIdOficina = "SELECT id_oficina FROM TB_LOGIN_OFICINA WHERE login_oficina = ?";
        String comandoBuscarIdProblema = "SELECT p.id_problema FROM TB_PROBLEMA p INNER JOIN TB_CARRO_CLIENTE cc ON p.id_carro = cc.id_carro" +
                " INNER JOIN TB_CLIENTE c ON c.id_cliente = cc.id_cliente INNER JOIN TB_CARRO ca ON ca.id_carro = cc.id_carro WHERE UPPER(ca.modelo_carro) = UPPER(?) AND UPPER(p.descricao_problema) LIKE UPPER(?)";

        try{
            PreparedStatement insercaoServico = conexao.prepareStatement(comandoInsercaoServico, new String[]{"id_servico"});
            PreparedStatement buscandoIdOficina = conexao.prepareStatement(comandoBuscaIdOficina);
            PreparedStatement buscandoIdProblema = conexao.prepareStatement(comandoBuscarIdProblema);

            buscandoIdOficina.setString(1,loginOficina);
            ResultSet resultadoOficina = buscandoIdOficina.executeQuery();
            int idOficina = 0;
            if(resultadoOficina.next()){
                idOficina = resultadoOficina.getInt("id_oficina");
            }

            buscandoIdProblema.setString(1,modeloCarro);
            buscandoIdProblema.setString(2,"%" + descricaoProblema + "%");

            ResultSet resultadoProblema = buscandoIdProblema.executeQuery();
            int idProblema = 0;
            if(resultadoProblema.next()){
                idProblema = resultadoProblema.getInt("id_problema");
            }


            insercaoServico.setString(1,servico.getCategoriaServico());
            insercaoServico.setInt(2,idOficina);
            insercaoServico.setInt(3,idProblema);
            insercaoServico.setDouble(4,servico.getPrecoServico());

            int servicoAdicionado = insercaoServico.executeUpdate();
            if (servicoAdicionado > 0) {
                System.out.println("Servico adicionado com sucesso!");
            } else {
                System.out.println("Erro ao adicionar serviço.");
            }

            resultadoOficina.close();
            resultadoProblema.close();
            insercaoServico.close();
            buscandoIdOficina.close();
            buscandoIdProblema.close();

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void inserirPeca(Peca peca,String modeloCarro,String descricaoProblema){

        String comandoInsercaoPeca = "INSERT INTO TB_PECA(id_problema,nome_peca,preco_peca,quantidade) VALUES" +
                "(?,?,?,?)";
        String comandoBuscarIdProblema = "SELECT p.id_problema FROM TB_PROBLEMA p INNER JOIN TB_CARRO_CLIENTE cc ON p.id_carro = cc.id_carro" +
                " INNER JOIN TB_CLIENTE c ON c.id_cliente = cc.id_cliente INNER JOIN TB_CARRO ca ON ca.id_carro = cc.id_carro WHERE UPPER(ca.modelo_carro) = UPPER(?) AND UPPER(p.descricao_problema) LIKE UPPER(?)";


        try{
            PreparedStatement buscandoIdProblema = conexao.prepareStatement(comandoBuscarIdProblema);
            PreparedStatement preparandoInsercaoPeca = conexao.prepareStatement(comandoInsercaoPeca, new String[]{"id_peca"});

            buscandoIdProblema.setString(1,modeloCarro);
            buscandoIdProblema.setString(2,"%" + descricaoProblema + "%");

            ResultSet resultadoProblema = buscandoIdProblema.executeQuery();
            int idProblema = 0;
            if(resultadoProblema.next()){
                idProblema = resultadoProblema.getInt("id_problema");
            }

            preparandoInsercaoPeca.setInt(1, idProblema );
            preparandoInsercaoPeca.setString(2, peca.getNomePeca());
            preparandoInsercaoPeca.setDouble(3, peca.getPrecoPeca());
            preparandoInsercaoPeca.setInt(4, peca.getQuantidade());


            int pecaAdicionada = preparandoInsercaoPeca.executeUpdate();
            if (pecaAdicionada > 0) {
                System.out.println("Peça adicionado com sucesso!");
            } else {
                System.out.println("Erro ao adicionar peça.");
            }
            resultadoProblema.close();
            preparandoInsercaoPeca.close();
            buscandoIdProblema.close();


        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void inserirOrcamento(Orcamento orcamento,String modeloCarro, String descricaoProblema){

        String comandoInsercaoOrcamento = "INSERT INTO TB_ORCAMENTO(data_orcamento,descricao,id_problema,valor) VALUES (?,?,?,?)";
        String comandoBuscarIdProblema = "SELECT p.id_problema FROM TB_PROBLEMA p INNER JOIN TB_CARRO_CLIENTE cc ON p.id_carro = cc.id_carro" +
                " INNER JOIN TB_CLIENTE c ON c.id_cliente = cc.id_cliente INNER JOIN TB_CARRO ca ON ca.id_carro = cc.id_carro WHERE UPPER(ca.modelo_carro)" +
                " = UPPER(?) AND UPPER(p.descricao_problema) LIKE UPPER(?)";

        String comandoBuscarPrecoServico = "SELECT s.preco_servico FROM TB_SERVICO s WHERE s.id_problema = ?";
        String comandoBuscaPrecoPeca = "SELECT (pe.preco_peca * pe.quantidade) AS total_valor_peca FROM TB_PECA pe WHERE pe.id_problema = ?";

        try{
            PreparedStatement buscandoPrecoPeca = conexao.prepareStatement(comandoBuscaPrecoPeca);
            PreparedStatement buscandoPrecoServico = conexao.prepareStatement(comandoBuscarPrecoServico);
            PreparedStatement buscandoIdProblema = conexao.prepareStatement(comandoBuscarIdProblema);
            PreparedStatement inserindoOrcamento = conexao.prepareStatement(comandoInsercaoOrcamento);



            buscandoIdProblema.setString(1,modeloCarro);
            buscandoIdProblema.setString(2,"%" + descricaoProblema + "%");

            ResultSet resultadoProblema = buscandoIdProblema.executeQuery();
            int idProblema = 0;
            if(resultadoProblema.next()){
                idProblema = resultadoProblema.getInt("id_problema");
            }


            buscandoPrecoServico.setInt(1,idProblema);
            ResultSet resultadoBuscaServico = buscandoPrecoServico.executeQuery();
            double valorServico = 0;
            if(resultadoBuscaServico.next()){
                valorServico = resultadoBuscaServico.getDouble("preco_servico");
            }

            buscandoPrecoPeca.setInt(1,idProblema);
            ResultSet resultadoBuscaPeca = buscandoPrecoPeca.executeQuery();
            double valorPeca = 0;
            if(resultadoBuscaPeca.next()){
                valorPeca = resultadoBuscaPeca.getDouble("total_valor_peca");
            }

            double valor = valorPeca + valorServico;

            inserindoOrcamento.setString(1,orcamento.getDataOrcamento());
            inserindoOrcamento.setString(2,orcamento.getDescricao());
            inserindoOrcamento.setInt(3,idProblema);
            inserindoOrcamento.setDouble(4,valor);

            int orcamentoAdicionado = inserindoOrcamento.executeUpdate();
            if (orcamentoAdicionado > 0) {
                System.out.println("Orçamento adicionado com sucesso!");
            } else {
                System.out.println("Erro ao adicionar orçamento.");
            }

            resultadoProblema.close();
            inserindoOrcamento.close();
            buscandoIdProblema.close();
            buscandoPrecoPeca.close();
            buscandoPrecoPeca.close();
            resultadoBuscaPeca.close();
            resultadoBuscaServico.close();


        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    public boolean logarOficina(String loginOficina, String senha){
        String comandoVerificacao = "SELECT * FROM TB_LOGIN_OFICINA WHERE login_oficina = ? AND senha = ?";

        try {
            PreparedStatement  preparandoEstado = conexao.prepareStatement(comandoVerificacao);
            preparandoEstado.setString(1, loginOficina);
            preparandoEstado.setString(2,senha);

            ResultSet resultSet = preparandoEstado.executeQuery();

            if(resultSet.next()){
                System.out.println("Logado com sucesso ");
                preparandoEstado.close();
                resultSet.close();
                return true;

            }else{
                System.out.println("Oficina não encontrada");
                preparandoEstado.close();
                resultSet.close();
                return false;
            }
        } catch (SQLException e ){
            throw new RuntimeException("Erro ao realizar consulta de dados, por favor tente se logar novamente");
        }

        }

    public boolean oficinaVerificacao(String loginOficina, String cnpj){
        String comandoVerificacao = "SELECT l.login_oficina, o.cnpj FROM TB_LOGIN_OFICINA l INNER JOIN TB_OFICINA o ON o.id_oficina = l.id_oficina " +
                "WHERE l.login_oficina = ? AND c.cnpj LIKE = ?";

        try{
            PreparedStatement preparandoVerificacao = conexao.prepareStatement(comandoVerificacao);
            preparandoVerificacao.setString(1,loginOficina);
            preparandoVerificacao.setString(2,cnpj);
            ResultSet resultSet = preparandoVerificacao.executeQuery();
            if(resultSet.next()){
                System.out.println("Oficina já existe no banco de dados");
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
