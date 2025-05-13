package br.com.fiap.singleton;

import br.com.fiap.model.Funcionario;

public class LoginSingleton {

    private static Funcionario funcionarioLogado;

    public static Funcionario login(Funcionario funcionario){
        if (funcionarioLogado == null){
            funcionarioLogado = funcionario;
            System.out.println("Tá logado familia");
        }
        return funcionarioLogado;
    }

    public static void logoff(){
        if (funcionarioLogado != null){
            funcionarioLogado = null;
        }
    }

    public static Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }
}
