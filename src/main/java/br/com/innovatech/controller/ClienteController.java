package br.com.innovatech.controller;

import br.com.innovatech.conn.ClienteDAO;
import br.com.innovatech.controller.dto.EnderecoClienteRequest;
import br.com.innovatech.controller.dto.LogarClienteRequest;
import br.com.innovatech.controller.dto.RealizarPagClienteRequest;
import br.com.innovatech.dominio.Cartao;
import br.com.innovatech.dominio.Cliente;
import br.com.innovatech.dominio.Endereco;
import br.com.innovatech.dominio.RepositorioCliente;
import br.com.innovatech.service.ClienteService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("cliente")
public class ClienteController {
    private RepositorioCliente clienteDAO;
    private ClienteService clienteService;
    public ClienteController(){
        clienteDAO = new ClienteDAO();
        clienteService = new ClienteService(clienteDAO);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirCliente(Cliente cliente){
        clienteService.inserirCliente(cliente);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }


    @POST
    @Path("/inserir-endereco")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirEndereco(EnderecoClienteRequest request){
        Endereco endereco = request.getEndereco();
        String login = request.getLogin();
        clienteService.inserirEndereco(endereco,login);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }


    @POST
    @Path("/realizar-pagamento")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response realizarPagamento(RealizarPagClienteRequest request){
        Cartao cartao  = request.getCartao();
        String login = request.getLogin();
        String modeloCarro = request.getModeloCarro();
        String descricaoProblema = request.getDescricaoProblema();

        try {
            clienteService.realizarPagamento(cartao, login, modeloCarro,descricaoProblema);
            return Response.status(Response.Status.CREATED).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao realizar pagamento: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/logar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logarCliente(LogarClienteRequest request){
        String login = request.getLogin();

        String senha = request.getSenha();
        clienteService.logarCliente(login, senha);
        return Response
                .status(Response.Status.OK)
                .entity(login)
                .build();
    }


}
