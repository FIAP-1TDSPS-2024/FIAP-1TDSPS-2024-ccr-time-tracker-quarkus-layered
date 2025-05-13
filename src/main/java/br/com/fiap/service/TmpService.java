package br.com.fiap.service;

import br.com.fiap.dto.TmpDTO;
import br.com.fiap.model.Estacao;
import br.com.fiap.model.Funcionario;
import br.com.fiap.model.Item;
import br.com.fiap.model.Linha;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TmpService {

    public TmpDTO tempoDeViagem(int estacaoPartidaId, int estacaoDestinoId) {

        Estacao estacaoPartida = (Estacao) Estacao.findByIdOptional(estacaoPartidaId).orElseThrow(() -> new NotFoundException("A estacao de partida não foi encontrada"));
        Estacao estacaoDestino = (Estacao) Estacao.findByIdOptional(estacaoDestinoId).orElseThrow(() -> new NotFoundException("A estacao de destino não foi encontrada"));

        List<Linha> linhasPartida = findByEstacao(estacaoPartidaId);
        List<Linha> linhasDestino = findByEstacao(estacaoDestinoId);

        int estacoesDistancia;

       if (linhasPartida == linhasDestino){
           estacoesDistancia = estacaoPartida.numero - estacaoDestino.numero;
       }
       else{

           int distanciaPartidaIntegracao;
           int distanciaIntegracaoDestino;

           if (estacaoPartida.numero > 6){
               distanciaPartidaIntegracao = estacaoPartida.numero - 6;
           }
           else{
               distanciaPartidaIntegracao = 6 - estacaoPartida.numero;
           }

           if (estacaoDestino.numero > 6){
               distanciaIntegracaoDestino = estacaoDestino.numero - 6;
           }
           else{
               distanciaIntegracaoDestino = 6 - estacaoDestino.numero;
           }

           estacoesDistancia = distanciaIntegracaoDestino + distanciaPartidaIntegracao;
       }

       return new TmpDTO(estacaoPartida, estacaoDestino, estacoesDistancia * 4);

    }

    public static List<Linha> findByEstacao(int estacaoId) {
        return Linha.find("SELECT l FROM Linha l JOIN l.estacoes e WHERE e.id = ?1", estacaoId).list();
    }

    public List<Linha> listAll(){
        return Linha.listAll();
    }
}
