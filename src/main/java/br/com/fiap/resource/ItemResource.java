package br.com.fiap.resource;

import br.com.fiap.model.Funcionario;
import br.com.fiap.model.Item;
import br.com.fiap.service.FuncionarioService;
import br.com.fiap.service.ItemService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("api/itens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ItemResource {

    @Inject
    ItemService itemService;

    @GET
    @Path("/{funcionarioId}")
    public Response listItensFuncionario(@PathParam("funcionarioId") int funcionarioId){
        List<Item> items = itemService.findByFuncionarioId(funcionarioId);

        if (items.isEmpty()){
            return Response.noContent().build();
        }
        else {
            return Response.ok(items).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id){
        Item item = itemService.findById(id);
        return Response.ok(item).build();
    }

    @POST
    @Path("/{funcionarioId}")
    public Response create(@PathParam("funcionarioId") int funcionarioId, Item itemExistente){
        try {
            Item novoItem = itemService.save(funcionarioId, itemExistente);
            return Response.created(URI.create("api/itens" + novoItem.id)).build();
        }
        catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, Item itemNovo){
        try {
            Item itemAtualizado = itemService.update(id, itemNovo);
            return Response.ok().build();
        }
        catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id){
        itemService.delete(id);
        return Response.noContent().build();
    }

}
