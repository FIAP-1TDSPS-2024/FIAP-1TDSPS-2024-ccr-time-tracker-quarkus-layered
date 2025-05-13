package br.com.fiap.exception;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {
        int statusCode = 500;
        String titulo = "Erro interno";
        String detalhe = ex.getMessage();

        if (ex instanceof NotFoundException) {
            statusCode = 404;
            titulo = "Recurso não encontrado";
        }
        else if (ex instanceof LoginInvalidoException) {
            statusCode = 400;
            titulo = "Erro de validação";
        }
        else if (ex instanceof AcessoNegadoexception) {
            statusCode = 403;
            titulo = "Acesso Negado";
        }
        else if (ex instanceof BadRequestException) {
            statusCode = 400;
            titulo = "Dados inválidos";
        }

        return Response.status(statusCode)
                .entity(new ErrorResponse(titulo, statusCode, detalhe))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
