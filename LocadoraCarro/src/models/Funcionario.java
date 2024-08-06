package models;

import java.util.ArrayList;
import java.util.List;

public abstract class Funcionario extends Pessoa {
    protected double porcentagemComissao;
    private double salario;
    private double comissaoTotal;
    private int idFuncionario;
    private static int somaId = 1;
    private double valorMeta;
    private Credenciais credenciais;
    private String cargo;
    private List<Carros> listaCarros;

    public Funcionario(String nome, String CPF, short idade, double salario, Credenciais credenciais) {
        super(nome, CPF, idade);
        this.idFuncionario = somaId++;
        this.salario = salario;
        this.porcentagemComissao = 0;
        this.credenciais = credenciais;
        this.listaCarros = new ArrayList<>();
        this.comissaoTotal = 0;
    }

    public void calcularValorReceber() {
        comissaoTotal = 0;
        for (Carros carro : listaCarros) {
            comissaoTotal +=  (porcentagemComissao * carro.getAluguel().getValorTotal());
        }
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getPorcentagemComissao() {
        return porcentagemComissao;
    }

    public void setPorcentagemComissao(double porcentagemComissao) {
        this.porcentagemComissao = porcentagemComissao;
    }

    public double getComissaoTotal() {
        return comissaoTotal;
    }

    public void setComissaoTotal(double comissaoTotal) {
        this.comissaoTotal = comissaoTotal;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public double getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(double valorMeta) {
        this.valorMeta = valorMeta;
    }

    public Credenciais getCredenciais() {
        return credenciais;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


    public void addCarro(Carros carro) {
        listaCarros.add(carro);
        calcularValorReceber();
    }


    @Override
    public String toString() {
        return nome;
    }
}
