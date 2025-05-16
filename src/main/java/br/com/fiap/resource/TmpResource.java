package br.com.fiap.resource;

import br.com.fiap.dto.TmpDTO;
import br.com.fiap.model.Item;
import br.com.fiap.model.Linha;
import br.com.fiap.service.ItemService;
import br.com.fiap.service.TmpService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("api/linhas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TmpResource {

    @Inject
    TmpService tmpService;

    @GET
    public Response listItensFuncionario() {
        List<Linha> linhas = tmpService.listAll();

        if (linhas.isEmpty()){
            return Response.noContent().build();
        }
        else {
            return Response.ok(linhas).build();
        }
    }

    @GET
    @Path("/{estacaoPartidaId}/{estacaoDestinoId}")
    public Response calcularTempoViagem(@PathParam("estacaoPartidaId") int estacaoPartidaId, @PathParam("estacaoDestinoId") int estacaoDestinoId){
        TmpDTO tmp = tmpService.tempoDeViagem(estacaoPartidaId, estacaoDestinoId);
        return Response.ok(tmp).build();
    }
}
