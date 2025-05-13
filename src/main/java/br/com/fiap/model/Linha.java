package br.com.fiap.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "LINHA")
public class Linha  extends PanacheEntity {

    @Column(name = "NOME", nullable = false, length = 100)
    public String nome;

    @Column(name = "SIGLA", nullable = false, length = 4)
    public String sigla;

    @Column(name = "NUMERO", nullable = false, length = 3)
    public int numero;

    @ManyToMany
    public List<Estacao> estacoes;
}
