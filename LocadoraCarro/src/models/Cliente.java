package models;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
    private List<Carros> carrosAlugados = new ArrayList<>();
    private String numHabilitacao;
    private int idCliente;
    private static int somaId = 1;

    public Cliente(String nome, String CPF, short idade, String numHabilitacao) {
        super(nome, CPF, idade);
        this.idCliente = somaId++;
        this.numHabilitacao = numHabilitacao;
    }

    public String getNumHabilitacao() {
        return numHabilitacao;
    }

    public void setNumHabilitacao(String numHabilitacao) {
        this.numHabilitacao = numHabilitacao;
    }

    public List<Carros> getCarrosAlugados() {
        return carrosAlugados;
    }


    public int getIdCliente() {
        return idCliente;
    }


    public void addCarro(Carros carro){
        carrosAlugados.add(carro);
    }

    @Override
    public String toString() {
        return nome;
    }
}
