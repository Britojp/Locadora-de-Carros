package models;

import java.util.ArrayList;
import java.util.List;

public class Locadora {
    private List<Carros> listaCarros = new ArrayList<>();
    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Funcionario> listaFuncionarios = new ArrayList<>();
    private double lucro;

    public Locadora(List<Carros> listaCarros, List<Cliente> listaClientes) {
        this.listaCarros = listaCarros;
        this.listaClientes = listaClientes;
        this.lucro = 0;
    }


    public double lucroTotal() {
        double lucroTotal = 0;
        for (Carros carro : listaCarros) {
            if (carro.aluguel.isAlugado()) {
                lucroTotal += carro.aluguel.calcularValorTotal();
            }
        }
        return lucroTotal;
    }

}

