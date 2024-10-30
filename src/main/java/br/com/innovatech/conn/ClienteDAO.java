package br.com.innovatech.conn;


import br.com.innovatech.dominio.*;

import java.sql.*;

public class ClienteDAO implements RepositorioCliente {

    private Connection conexao;

    public ClienteDAO(){
        conexao = new ConnectionFactory().pegarConexao();
    }

    public void inserirCliente(Cliente cliente) {

        String comandoInsercao = "INSERT INTO TB_CLIENTE(nome_cliente,cpf_cliente,idade_cliente) VALUES (?,?,?)";
        String comandoInsercao2 = "INSERT INTO TB_LOGIN(id_cliente,login, senha) VALUES (?,?,?)";
        String comandoInsercao3 = "INSERT INTO TB_CONTATO(id_cliente,telefone,email) VALUES(?,?,?)";
        try {
            PreparedStatement preparandoInsercao = conexao.prepareStatement(comandoInsercao, new String[]{"id_cliente"});
            preparandoInsercao.setString(1, cliente.getNome());
            preparandoInsercao.setString(2, cliente.getCpf());
            preparandoInsercao.setInt(3, cliente.getIdade());

            preparandoInsercao.executeUpdate();

            ResultSet pegandoId = preparandoInsercao.getGeneratedKeys();

            if(pegandoId.next()){
                int idCliente = pegandoId.getInt(1);
                PreparedStatement preparandoInsercao2 = null;
                PreparedStatement preparandoInsercao3 = null;
                try {
                    preparandoInsercao2 = conexao.prepareStatement(comandoInsercao2);

                    preparandoInsercao2.setInt(1, idCliente);
                    preparandoInsercao2.setString(2, cliente.getLogin());
                    preparandoInsercao2.setString(3, cliente.getSenha());

                    preparandoInsercao2.executeUpdate();

                     preparandoInsercao3 = conexao.prepareStatement(comandoInsercao3);

                    preparandoInsercao3.setInt(1, idCliente);
                    preparandoInsercao3.setString(2, cliente.getTelefone());
                    preparandoInsercao3.setString(3, cliente.getEmail());

                    preparandoInsercao3.executeUpdate();


                    System.out.println("Cadastro realizado com sucesso!");
                }finally {
                    // Fechando os PreparedStatement de login e contato
                    if (preparandoInsercao2 != null) preparandoInsercao2.close();
                    if (preparandoInsercao3 != null) preparandoInsercao3.close();
                }
            }

            preparandoInsercao.close();

        }

        catch (SQLException e) {
            throw new RuntimeException("Não foi possivel inserir os dados" + e);
        }

    }
    public boolean logarCliente(Cliente cliente){
        String comandoVerificacao = "SELECT * FROM TB_LOGIN WHERE login = ? AND senha = ?";

        try {
            PreparedStatement  preparandoEstado = conexao.prepareStatement(comandoVerificacao);
            preparandoEstado.setString(1, cliente.getLogin());
            preparandoEstado.setString(2,cliente.getSenha());

            ResultSet resultSet = preparandoEstado.executeQuery();

            if(resultSet.next()){
                    System.out.println("Logado com sucesso ");
                    preparandoEstado.close();
                    resultSet.close();
                    return true;

                }else{
                    System.out.println("Usuario não encontrado");
                    preparandoEstado.close();
                    resultSet.close();
                    return false;
                }


        } catch (SQLException e ){
            throw new RuntimeException("Erro ao realizar consulta de dados, por favor tente se logar novamente");
        }


    }

    public void inserirEndereco(Endereco endereco, String login){

        String comandoInsercaoEndereco = "INSERT INTO TB_ENDERECO(cep,cidade,complemento,id_cliente,logradouro,numero,uf) VALUES(?,?,?,?,?,?,?)";
        String comandoBuscarIdCliente = "SELECT id_cliente FROM TB_LOGIN WHERE login = ?";

        try {
            PreparedStatement buscandoIdCliente = conexao.prepareStatement(comandoBuscarIdCliente);
            buscandoIdCliente.setString(1, login);
            ResultSet resultadoCliente = buscandoIdCliente.executeQuery();
            int idCliente = 0;
            if(resultadoCliente.next()){
                idCliente = resultadoCliente.getInt("id_cliente");
            }
            PreparedStatement preparanInsercaoEndereco = conexao.prepareStatement(comandoInsercaoEndereco);
            preparanInsercaoEndereco.setString(1,endereco.getCep());
            preparanInsercaoEndereco.setString(2,endereco.getCidade());
            preparanInsercaoEndereco.setString(3,endereco.getComplemento());
            preparanInsercaoEndereco.setInt(4,idCliente);
            preparanInsercaoEndereco.setString(5,endereco.getLogradouro());
            preparanInsercaoEndereco.setInt(6,endereco.getNumero());
            preparanInsercaoEndereco.setString(7,endereco.getUf());

            int enderecoAdicionado = preparanInsercaoEndereco.executeUpdate();
            if (enderecoAdicionado > 0) {
                System.out.println("Endereço inserido com sucesso!");
            } else {
                System.out.println("Erro ao inserir endereço.");
            }

            preparanInsercaoEndereco.close();
            resultadoCliente.close();
            buscandoIdCliente.close();

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void realizarPagamento(Cartao cartao, String login, String modeloCarro){

        String comandoInsercaoEndereco = "INSERT INTO TB_PAGAMENTO(cvv,id_cliente,id_problema,numero_cartao,validade) VALUES(?,?,?,?,?)";
        String comandoBuscarIdCliente = "SELECT id_cliente FROM TB_LOGIN WHERE login = ?";
        String comandoBuscarIdProblema = "SELECT p.id_problema FROM TB_PROBLEMA p INNER JOIN TB_CARRO_CLIENTE cc ON p.id_carro = cc.id_carro" +
                " INNER JOIN TB_CLIENTE c ON c.id_cliente = cc.id_cliente INNER JOIN TB_CARRO ca ON ca.id_carro = cc.id_carro WHERE ca.modelo_carro = ?";

        try {
            PreparedStatement buscandoIdCliente = conexao.prepareStatement(comandoBuscarIdCliente);
            PreparedStatement buscandoIdProblema = conexao.prepareStatement(comandoBuscarIdProblema);

            buscandoIdCliente.setString(1, login);
            ResultSet resultadoCliente = buscandoIdCliente.executeQuery();
            int idCliente = 0;
            if(resultadoCliente.next()){
                idCliente = resultadoCliente.getInt("id_cliente");
            }

            buscandoIdProblema.setString(1,modeloCarro);
            ResultSet resultadoProblema = buscandoIdProblema.executeQuery();
            int idProblema = 0;
            if(resultadoProblema.next()){
                idProblema = resultadoProblema.getInt("id_problema");
            }


            PreparedStatement preparanInsercaoPagamento = conexao.prepareStatement(comandoInsercaoEndereco);
            preparanInsercaoPagamento.setInt(1,cartao.getCVV());
            preparanInsercaoPagamento.setInt(2,idCliente);
            preparanInsercaoPagamento.setInt(3,idProblema);
            preparanInsercaoPagamento.setString(4,cartao.getNumero());
            preparanInsercaoPagamento.setDate(5, Date.valueOf(cartao.getValidade()));




            int pagamentoAdicionado = preparanInsercaoPagamento.executeUpdate();
            if (pagamentoAdicionado > 0) {
                System.out.println("Pagamento realizado com sucesso!");
            } else {
                System.out.println("Erro ao realizar pagamento.");
            }

            preparanInsercaoPagamento.close();
            resultadoCliente.close();
            buscandoIdCliente.close();
            buscandoIdProblema.close();

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }



    public void fecharConexao(){
        try{
            conexao.close();
        }catch (SQLException e){
            System.out.println("Não foi possivel fechar a conexão com o banco: " + e.getMessage());
        }
    }

}