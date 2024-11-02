package br.com.innovatech.conn;

import br.com.innovatech.dominio.CarroCliente;

import br.com.innovatech.dominio.Problema;
import br.com.innovatech.dominio.RepositorioCarro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarroClienteDAO implements RepositorioCarro {

    private Connection conexao;

    public CarroClienteDAO(){
        conexao = new ConnectionFactory().pegarConexao();
    }

    public void inserirCarro(String login, CarroCliente carro){

        String comandoInsercao = "INSERT INTO TB_CARRO(modelo_carro,marca,ano_fabricacao) VALUES (?,?,?)";
        String comandoInsercao2 = "INSERT INTO TB_CARRO_CLIENTE(id_cliente,id_carro) VALUES (?,?)";
        String comandoBuscarIdCliente = "SELECT id_cliente FROM TB_LOGIN WHERE login = ?";
        try {
            PreparedStatement preparandoInsercao = conexao.prepareStatement(comandoInsercao,new String[]{"id_carro"});

            preparandoInsercao.setString(1,carro.getModeloCarro());
            preparandoInsercao.setString(2,carro.getmarca());
            preparandoInsercao.setInt(3, carro.getAno());
            preparandoInsercao.executeUpdate();


            ResultSet pegandoIdCarro = preparandoInsercao.getGeneratedKeys();
            if(pegandoIdCarro.next()){

                int idCarro = pegandoIdCarro.getInt(1);

                PreparedStatement buscandoIdCliente = conexao.prepareStatement(comandoBuscarIdCliente);
                buscandoIdCliente.setString(1,login);
                ResultSet resultadoCliente = buscandoIdCliente.executeQuery();
                int idCliente = 0;
                if(resultadoCliente.next()){
                    idCliente = resultadoCliente.getInt("id_cliente");
                }

                PreparedStatement preparandoInsercao2 = null;
                try{
                    preparandoInsercao2 = conexao.prepareStatement(comandoInsercao2);
                    preparandoInsercao2.setInt(1,idCliente);
                    preparandoInsercao2.setInt(2,idCarro);

                    preparandoInsercao2.executeUpdate();

                    preparandoInsercao2.close();
                    buscandoIdCliente.close();
                    pegandoIdCarro.close();

                }catch (SQLException e){

                    System.out.println(e.getMessage());
                }
            }
            preparandoInsercao.close();
            System.out.println("Carro inserido com sucesso!");

        }catch(SQLException e){

            throw new RuntimeException("Não foi possivel inserir os dados" + e);
        }


    }
    public ArrayList<CarroCliente> lerCarrosCliente(String login) {
        String comandoLeitura = " SELECT ca.* FROM TB_CARRO ca" +
                " INNER JOIN TB_CARRO_CLIENTE cc ON ca.id_carro = cc.id_carro" +
                " INNER JOIN TB_CLIENTE c ON cc.id_cliente = c.id_cliente" +
                " INNER JOIN TB_LOGIN l ON c.id_cliente = l.id_cliente" +
                " WHERE l.login = ?";

        ArrayList<CarroCliente> carros = new ArrayList<>();

        try{
            PreparedStatement preparandoLeitura = conexao.prepareStatement(comandoLeitura);
            preparandoLeitura.setString(1,login);

            ResultSet resultSet = preparandoLeitura.executeQuery();

            while(resultSet.next()){
                CarroCliente carroCliente = new CarroCliente(
                        resultSet.getString("modelo_carro"),
                        resultSet.getString("marca"),
                        resultSet.getInt("ano_fabricacao")
                );


                carros.add(carroCliente);
            }
            preparandoLeitura.close();
            resultSet.close();
            return carros;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void atualizarInformacaoCarro(String modelo, String marca, int ano, CarroCliente carroCliente, String login) {
        String comandoBuscarIdCliente = "SELECT id_cliente FROM TB_LOGIN WHERE login = ?";
        String comandoBuscarIdCarro = "SELECT id_carro FROM TB_CARRO WHERE UPPER(modelo_carro) = UPPER(?) AND UPPER(marca) = UPPER(?) AND ano_fabricacao = ?";
        String comandoAtualizacao = "UPDATE TB_CARRO SET modelo_carro = ?, marca = ?, ano_fabricacao = ? WHERE id_carro = ? AND id_carro IN (SELECT id_carro FROM TB_CARRO_CLIENTE WHERE id_cliente = ?)";

        try {
            PreparedStatement buscandoIdCliente = conexao.prepareStatement(comandoBuscarIdCliente);
            buscandoIdCliente.setString(1, login);
            ResultSet resultadoCliente = buscandoIdCliente.executeQuery();

            int idCliente = 0;
            if (resultadoCliente.next()) {
                idCliente = resultadoCliente.getInt("id_cliente");
            }

            PreparedStatement buscandoIdCarro = conexao.prepareStatement(comandoBuscarIdCarro);
            buscandoIdCarro.setString(1, carroCliente.getModeloCarro());
            buscandoIdCarro.setString(2, carroCliente.getmarca());
            buscandoIdCarro.setInt(3, carroCliente.getAno());

            ResultSet resultadoCarro = buscandoIdCarro.executeQuery();

            int idCarro = 0;
            if (resultadoCarro.next()) {
                idCarro = resultadoCarro.getInt("id_carro");
            }

            PreparedStatement preparandoAtualizacao = conexao.prepareStatement(comandoAtualizacao);
            preparandoAtualizacao.setString(1, modelo);
            preparandoAtualizacao.setString(2, marca);
            preparandoAtualizacao.setInt(3, ano);
            preparandoAtualizacao.setInt(4, idCarro);
            preparandoAtualizacao.setInt(5, idCliente);

            int CarroAtualizado = preparandoAtualizacao.executeUpdate();

            if (CarroAtualizado > 0) {
                System.out.println("Carro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum carro encontrado para atualização.");
            }

            resultadoCliente.close();
            resultadoCarro.close();
            buscandoIdCliente.close();
            buscandoIdCarro.close();
            preparandoAtualizacao.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar as informações do carro: " + e.getMessage());
        }
    }

    public void deletarCarro(String modeloCarro, String login){

        String comandoDelete = "DELETE FROM TB_CARRO_CLIENTE " +
                "WHERE id_carro IN (" +
                "SELECT c.id_carro FROM TB_CARRO c " +
                "INNER JOIN TB_CARRO_CLIENTE cc ON c.id_carro = cc.id_carro " +
                "INNER JOIN TB_CLIENTE cl ON cl.id_cliente = cc.id_cliente " +
                "INNER JOIN TB_LOGIN l ON l.id_cliente = cl.id_cliente " +
                "WHERE UPPER(c.modelo_carro) = UPPER(?) AND l.login = ?)";

        try{
            PreparedStatement preparandoDelete = conexao.prepareStatement(comandoDelete);
            preparandoDelete.setString(1,modeloCarro);

            preparandoDelete.setString(2,login);

            int carroDeletado = preparandoDelete.executeUpdate();
            if (carroDeletado > 0) {
                System.out.println("Carro deletado com sucesso!");
            } else {
                System.out.println("Nenhum carro encontrado para deletar.");
            }

            preparandoDelete.close();

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar carro: " + e.getMessage());
        }

    }

    public void inserirProblema(Problema problema, String login){

        String comandoInsercaoProblema = "INSERT INTO TB_PROBLEMA(descricao_problema, id_carro, quantidade_problema) VALUES(?,?,?)";
        String comandoBuscandoIdCliente = "SELECT id_cliente FROM TB_LOGIN WHERE login = ?";
        String comandoBuscandoIdCarro = "SELECT c.id_carro FROM TB_CARRO c INNER JOIN TB_CARRO_CLIENTE cc ON cc.id_carro = c.id_carro INNER JOIN TB_CLIENTE cl" +
                " ON cl.id_cliente = cc.id_cliente WHERE cl.id_cliente = ?";

        try{
            PreparedStatement buscandoIdCliente = conexao.prepareStatement(comandoBuscandoIdCliente);
            PreparedStatement buscandoIdCarro = conexao.prepareStatement(comandoBuscandoIdCarro);
            PreparedStatement inserindoProblema = conexao.prepareStatement(comandoInsercaoProblema);

            buscandoIdCliente.setString(1, login);
            ResultSet resultadoCliente = buscandoIdCliente.executeQuery();
            int idCliente = 0;
            if(resultadoCliente.next()){
                idCliente = resultadoCliente.getInt("id_cliente");
            }

            buscandoIdCarro.setInt(1,idCliente);
            ResultSet resultadoCarro = buscandoIdCarro.executeQuery();
            int idCarro = 0;
            if(resultadoCarro.next()){
                idCarro = resultadoCarro.getInt("id_carro");
            }

            inserindoProblema.setString(1,problema.getDescricaoProblema());
            inserindoProblema.setInt(2,idCarro);
            inserindoProblema.setInt(3,problema.getQuantidadeProblema());

            int problemaInserido = inserindoProblema.executeUpdate();
            if (problemaInserido > 0) {
                System.out.println("Problema inserido com sucesso!");
            } else {
                System.out.println("Erro ao inserir problema.");
            }

            inserindoProblema.close();
            buscandoIdCarro.close();
            buscandoIdCliente.close();
            resultadoCarro.close();
            resultadoCliente.close();

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
