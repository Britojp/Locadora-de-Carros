package models;

public class Gerente extends Funcionario {

    public Gerente(String nome, String CPF, short idade, double salario, Credenciais credenciais) {
        super(nome, CPF, idade, salario, credenciais);
        setCargo("Gerente");
        credenciais.setAdmin(true);
        setPorcentagemComissao(0);
        setComissaoTotal(0);
        setValorMeta(0);
    }

}
