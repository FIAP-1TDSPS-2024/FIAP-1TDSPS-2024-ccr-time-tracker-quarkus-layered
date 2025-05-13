package br.com.fiap.service;

import br.com.fiap.model.Funcionario;
import br.com.fiap.model.Item;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class ItemService {

    public List<Item> findByFuncionarioId(int funcionarioId) {
        return Item.list("funcionario.id", funcionarioId);
    }

    public Item findById(int id){
        return (Item) Item.findByIdOptional(id).orElseThrow(() -> new NotFoundException("Item não foi encontrado"));
    }

    @Transactional
    public Item save(int idFuncionario, Item item){
        item.funcionario = Funcionario.findById(idFuncionario);
        validarItem(item);
        Item.persist(item);
        return item;
    }

    @Transactional
    public Item update(int id, Item itemNovo){
        Item itemExistente = findById(id);

        validarItem(itemNovo);

        itemExistente.nome = itemNovo.nome;
        itemExistente.abreviacao = itemNovo.abreviacao;
        itemExistente.url = itemNovo.url;
        itemExistente.favorito = itemNovo.favorito;
        itemExistente.funcionario = itemNovo.funcionario;

        return itemExistente;
    }

    @Transactional
    public void delete(int id){
        Item.deleteById(id);
    }

    private void validarItem(Item item){
        if (item.nome == null){
            throw new BadRequestException("O nome do item não pode ser nulo");
        }
        else if (item.url == null){
            throw new BadRequestException("A url do item não pode ser nula");
        }
    }
}
