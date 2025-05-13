package br.com.fiap.dto;

import br.com.fiap.model.Estacao;

public record TmpDTO (Estacao estacaoPartida, Estacao estacaoDestino, int tempoMintutos){
}
