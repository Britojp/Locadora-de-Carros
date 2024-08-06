package models;

public class Aluguel {
    private double valorAluguel;
    private double valorTotal;
    private boolean alugado;
    private int tempoAluguel;
    private Cliente cliente;
    private Funcionario funcionario;

    public Aluguel() {
        this.alugado = false;
        this.tempoAluguel = 0;
        this.valorAluguel = 0;
    }

    public Aluguel(double valorCarro, Cliente cliente, Funcionario funcionario, int tempoAluguel) {
        this.valorAluguel = valorCarro / 100;
        this.alugado = true;
        this.tempoAluguel = tempoAluguel;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.valorTotal = calcularValorTotal();
    }

    public double calcularValorTotal() {
        return tempoAluguel * valorAluguel;
    }

    public double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }

    public int getTempoAluguel() {
        return tempoAluguel;
    }

    public void setTempoAluguel(int tempoAluguel) {
        this.tempoAluguel = tempoAluguel;
        this.valorTotal = calcularValorTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
