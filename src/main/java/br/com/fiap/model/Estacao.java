package br.com.fiap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ESTACAO")
public class Estacao extends PanacheEntity {

    @Column(name = "NOME", nullable = false, length = 100)
    public String nome;

    @Column(name = "SIGLA", nullable = false, length = 4)
    public String sigla;

    @Column(name = "ENDERECO", nullable = false, length = 255)
    public String endereco;

    @Column(name = "IMPLANTADA", nullable = false)
    public boolean implantada; // Se a estação já foi implantada ou está em construção

    @Column(name = "NUMERO", nullable = false, length = 3)
    public int numero; // Numero desde a estacao Julio prestes para cálculo de tempo de viagem

    @JsonIgnore
    @ManyToMany(mappedBy = "estacoes")
    public List<Linha> linhas;
}
