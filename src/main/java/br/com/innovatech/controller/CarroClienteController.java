package br.com.innovatech.controller;


import br.com.innovatech.conn.CarroClienteDAO;
import br.com.innovatech.controller.dto.AttCarroClienteRequest;
import br.com.innovatech.controller.dto.CarroClienteRequest;
import br.com.innovatech.controller.dto.InserirProblemaRequest;
import br.com.innovatech.dominio.CarroCliente;
import br.com.innovatech.dominio.Problema;
import br.com.innovatech.dominio.RepositorioCarro;
import br.com.innovatech.service.CarroClienteService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("carros")
public class CarroClienteController {

    private RepositorioCarro carroDAO;
    private CarroClienteService carroClienteService;
    public CarroClienteController(){
        carroDAO = new CarroClienteDAO();
        carroClienteService = new CarroClienteService(carroDAO);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirCarro(
            CarroClienteRequest request){

            String login =  request.getLogin();
            CarroCliente carroCliente = request.getCarroCliente();
            carroClienteService.inserirCarro(login, carroCliente);
            return Response.
                    status(Response.Status.CREATED)
                    .build();
    }

    @GET
    @Path("/carros-cliente/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response lerCarrosCliente(
            @PathParam("login") String login){

        Response.Status status = null;

        ArrayList<CarroCliente> carros = carroClienteService.lerCarrosCliente(login);
        if(login == null) status = Response.Status.NOT_FOUND;
        else status = Response.Status.OK;
        return Response
                .status(status)
                .entity(carros)
                .build();
    }
    @PUT
    @Path("/atualizar-carro")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarInformacaoCarro(AttCarroClienteRequest request){
        String modelo = request.getModelo();
        String marca= request.getMarca();
        int ano = request.getAno();
        CarroCliente carroCliente = request.getCarroCliente();
        String login = request.getLogin();
        carroClienteService.atualizarInformacaoCarro(modelo,marca,ano,carroCliente,login);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarCarro(@QueryParam("modeloCarro") String modeloCarro, @QueryParam("login") String login){

          carroClienteService.deletarCarro(modeloCarro,login);

        return Response
                .status(Response.Status.NO_CONTENT)
                .entity("Método deletarCarro foi chamado")
                .build();

    }

    @POST
    @Path("/inserir-problema")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirProblema(InserirProblemaRequest request){
        Problema problema = request.getProblema();
        String login = request.getLogin();
        carroClienteService.inserirProblema(problema,login);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

}
