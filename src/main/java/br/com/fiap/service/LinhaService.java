package br.com.fiap.service;

import br.com.fiap.model.Estacao;
import br.com.fiap.model.Linha;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class LinhaService {

    public List<Linha> listAll(){
        return Linha.listAll();
    }
}
