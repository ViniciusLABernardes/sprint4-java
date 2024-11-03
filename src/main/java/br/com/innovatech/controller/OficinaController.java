package br.com.innovatech.controller;

import br.com.innovatech.conn.OficinaDAO;
import br.com.innovatech.controller.dto.InserirOrcamentoOficinaRequest;
import br.com.innovatech.controller.dto.InserirPecaOficinaRequest;
import br.com.innovatech.controller.dto.InserirServicoOficinaRequest;
import br.com.innovatech.controller.dto.LogarOficinaRequest;
import br.com.innovatech.dominio.*;
import br.com.innovatech.service.OficinaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.ArrayList;

@Path("oficinas")
public class OficinaController {
    private RepositorioOficina oficinaDAO;
    private OficinaService oficinaService;
    public OficinaController(){
        oficinaDAO = new OficinaDAO();
        oficinaService = new OficinaService(oficinaDAO);
    }
    @OPTIONS
    public Response handleCorsPreflight() {
        return Response
                .ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Methods", "POST, OPTIONS")
                .build();
    }
    @POST
    @Path("/inserir-servico")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirServico(InserirServicoOficinaRequest inserirServico){
       Servico servico =  inserirServico.getServico();
       String loginOficina = inserirServico.getLoginOficina();
       String modeloCarro = inserirServico.getModeloCarro();
       String descricaoProblema = inserirServico.getDescricaoProblema();
        oficinaService.inserirServico(servico, loginOficina, modeloCarro,descricaoProblema);
        return Response
                .status(Response.Status.CREATED)
                .build();

    }


    @POST
    @Path("/inserir-peca")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirPeca(InserirPecaOficinaRequest inserirPeca){
        Peca peca = inserirPeca.getPeca();
        String modeloCarro = inserirPeca.getModeloCarro();
        String descricaoProblema = inserirPeca.getDescricaoProblema();
        oficinaService.inserirPeca(peca,modeloCarro,descricaoProblema);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response lerOficinas(){
        ArrayList<Oficina> oficinas = oficinaService.lerOficinas();

        return Response
                .status(Response.Status.OK)
                .entity(oficinas)
                .build();
    }

    @GET
    @Path("/problemas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response lerProblemas(){
        ArrayList<Problema> problemas = oficinaService.lerProblemas();
        return Response
                .status(Response.Status.OK)
                .entity(problemas)
                .build();
    }

    @POST
    @Path("/inserir-orcamento")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirOrcamento(InserirOrcamentoOficinaRequest inserirOrcamento){
        Orcamento orcamento = inserirOrcamento.getOrcamento();
        String modeloCarro = inserirOrcamento.getModeloCarro();
        String descricaoProblema = inserirOrcamento.getDescricaoProblema();
        oficinaService.inserirOrcamento(orcamento,modeloCarro,descricaoProblema);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/logar")
    @Produces(MediaType.APPLICATION_JSON)

    public Response logarOficina(LogarOficinaRequest request){
        String loginOficina = request.getLoginOficina();
        String senha = request.getSenha();
        oficinaService.logarOficina(loginOficina, senha);
        return Response
                .status(Response.Status.OK)
                .entity(loginOficina)
                .build();
    }
}
