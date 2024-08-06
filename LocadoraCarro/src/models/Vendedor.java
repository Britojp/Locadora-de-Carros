package models;

public class Vendedor extends Funcionario {

    public Vendedor(String nome, String CPF, short idade, double salario, Credenciais credenciais) {
        super(nome, CPF, idade, salario, credenciais);
        setCargo("Vendedor");
        setPorcentagemComissao(0.04);
        setValorMeta(5*getSalario());
    }
}
