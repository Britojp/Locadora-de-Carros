package models;

public class Carros {
    private int idCarro;
    private String placaCarro;
    private double valorCarro;
    private String nomeCarro;
    private String corCarro;
    private static int somaId = 1;
    public Aluguel aluguel;

   public Carros(String placaCarro, Aluguel aluguel, double valorCarro, String nomeCarro, String corCarro){
        this.idCarro = somaId;
        somaId++;
        this.placaCarro = placaCarro;
        this.valorCarro = valorCarro;
        this.nomeCarro = nomeCarro;
        this.corCarro = corCarro;
        this.aluguel = aluguel;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public double getValorCarro() {
        return valorCarro;
    }

    public void setValorCarro(double valorCarro) {
        this.valorCarro = valorCarro;
    }

    public String getNomeCarro() {
        return nomeCarro;
    }

    public void setNomeCarro(String nomeCarro) {
        this.nomeCarro = nomeCarro;
    }

    public String getCorCarro() {
        return corCarro;
    }

    public void setCorCarro(String corCarro) {
        this.corCarro = corCarro;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }
    @Override
    public String toString() {
        return nomeCarro;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }
}
