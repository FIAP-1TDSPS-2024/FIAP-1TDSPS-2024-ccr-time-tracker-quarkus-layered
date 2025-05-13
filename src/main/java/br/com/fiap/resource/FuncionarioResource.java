package br.com.fiap.resource;

import br.com.fiap.model.Funcionario;
import br.com.fiap.service.FuncionarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("api/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class FuncionarioResource {

    @Inject
    FuncionarioService funcionarioService;

    @GET
    public Response listAll(){
        List<Funcionario> funcionarios = funcionarioService.listAll();
        return Response.ok(funcionarios).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id){
        Funcionario funcionario = funcionarioService.findById(id);
        return Response.ok(funcionario).build();
    }

    @POST
    public Response create(Funcionario funcionario){
        Funcionario novoFuncionario = funcionarioService.save(funcionario);
        return Response.created(URI.create("api/funcionarios" + novoFuncionario.id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, Funcionario funcionarioNovo){
        Funcionario novoFuncionario = funcionarioService.update(id, funcionarioNovo);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id){
        funcionarioService.delete(id);
        return Response.noContent().build();
    }

}
