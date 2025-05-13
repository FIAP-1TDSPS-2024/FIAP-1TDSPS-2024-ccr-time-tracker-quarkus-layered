package br.com.fiap.service;

import br.com.fiap.dto.LoginFuncionarioDTO;
import br.com.fiap.exception.AcessoNegadoexception;
import br.com.fiap.exception.LoginInvalidoException;
import br.com.fiap.model.Funcionario;
import br.com.fiap.model.Item;
import br.com.fiap.singleton.LoginSingleton;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class FuncionarioService {

    public Funcionario login(LoginFuncionarioDTO login){

        if ((login.emailFuncionario() != null) && (!login.senhaFuncionario().isEmpty())){
            Funcionario funcionarioLogado = Funcionario.find("email = :email and senha = :senha",
                    Parameters.with("email", login.emailFuncionario()).and("senha", login.senhaFuncionario())).firstResult();

            if (funcionarioLogado == null) {
                throw new LoginInvalidoException("Email ou senha inválidos.");
            }

            LoginSingleton.login(funcionarioLogado);

            return funcionarioLogado;
        }
        else {
            throw new LoginInvalidoException("Campos de login não podem estar vazios.");
        }
    }

    public void logoff(){
        LoginSingleton.logoff();
    }

    public List<Funcionario> listAll(){

        return Funcionario.listAll();
    }

    public Funcionario findById(int id){

        return (Funcionario) Funcionario.findByIdOptional(id).orElseThrow(() -> new NotFoundException("Não foi encontrado"));
    }

    @Transactional
    public Funcionario save(Funcionario funcionario){
        verificapermissao();

        verificaFuncionario(funcionario);

        Funcionario.persist(funcionario);
        return funcionario;
    }

    @Transactional
    public Funcionario update(int id, Funcionario funcionarioNovo){

        verificapermissao();

        verificaFuncionario(funcionarioNovo);

        Funcionario funcionarioExistente = findById(id);

        funcionarioExistente.nome = funcionarioNovo.nome;
        funcionarioExistente.cpf = funcionarioNovo.cpf;
        funcionarioExistente.cargo = funcionarioNovo.cargo;
        funcionarioExistente.permissao = funcionarioNovo.permissao;
        funcionarioExistente.email = funcionarioNovo.email;
        funcionarioExistente.senha = funcionarioNovo.senha;

        return funcionarioExistente;
    }

    @Transactional
    public void delete(int id){
        verificapermissao();
        Funcionario.deleteById(id);
    }

    private void verificapermissao(){
        if (LoginSingleton.getFuncionarioLogado() == null){
            throw new AcessoNegadoexception("Você deve estar logado para acessar este método");
        }
        else if (LoginSingleton.getFuncionarioLogado().permissao < 1){
            throw new AcessoNegadoexception("Você deve ter permissão de nível 1 ou superior para acessar este método");
        }
    }

    private void verificaFuncionario(Funcionario funcionario){
        if (funcionario.nome == null){
            throw new BadRequestException("O nome do funcionário não pode ser nulo");
        }
        else if (funcionario.cpf == null){
            throw new BadRequestException("O cpf do funcionário não pode ser nulo");
        }
        else if (!funcionario.cpf.matches("^\\d{11}$")){
            throw new BadRequestException("O cpf do funcionário deve ter apenas 11 caracteres e apenas números");
        }
        else if (!funcionario.email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            throw new BadRequestException("Insira um e-mail válido");
        }
        else if (funcionario.cargo == null) {
            throw new BadRequestException("O cargo do funcionário não pode ser nulo");
        }
        else if (funcionario.senha == null) {
            throw new BadRequestException("A senha do funcionário não pode ser nulo");
        }
    }
}
