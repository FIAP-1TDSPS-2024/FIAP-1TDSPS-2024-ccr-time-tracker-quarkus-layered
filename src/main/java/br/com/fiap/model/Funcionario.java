package br.com.fiap.model;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.Item;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario  extends PanacheEntity {

    @Column(name = "NOME", nullable = false, length = 100)
    public String nome;

    @Column(name = "CPF", nullable = false, length = 14)
    public String cpf;

    @Column(name = "EMAIL", nullable = false, length = 30)
    public String email;

    @Column(name = "SENHA", nullable = false, length = 40)
    public String senha;

    @Column(name = "CARGO",nullable = false, length = 100)
    public String cargo;

    @Column(name = "PERMISSAO",nullable = false, length = 2)
    public int permissao;

    @OneToMany(mappedBy = "funcionario")
    public List<Item> itens;

    public Funcionario() {
    }
}
