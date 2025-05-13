package br.com.fiap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "ITEM")
public class Item  extends PanacheEntity {

    @Column(name = "NOME", nullable = false, length = 100)
    public String nome;

    @Column(name = "ABREVIACAO", length = 20)
    public String abreviacao;

    @Column(name = "URL", nullable = false)
    public String url;

    @Column(name = "FAVORITO", nullable = false, length = 100)
    public boolean favorito;

    @JsonIgnore
    @ManyToOne
    public Funcionario funcionario;

    public Item() {
    }
}
