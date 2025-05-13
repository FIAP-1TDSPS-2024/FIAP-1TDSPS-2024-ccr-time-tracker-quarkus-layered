package br.com.fiap.resource;

import br.com.fiap.dto.LoginFuncionarioDTO;
import br.com.fiap.model.Funcionario;
import br.com.fiap.service.FuncionarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("api/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class LoginResource {

    @Inject
    FuncionarioService funcionarioService;

    @GET
    public Response login(LoginFuncionarioDTO login){
        Funcionario funcionarioLogado = funcionarioService.login(login);
        return Response.ok(funcionarioLogado).build();
    }

    @GET
    @Path("/exit")
    public Response logoff(){
        funcionarioService.logoff();
        return Response.ok().build();
    }



}