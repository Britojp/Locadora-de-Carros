import java.util.ArrayList;

import arqs.CarregarArquivos;
import arqs.SalvarArquivos;
import telas.*;
import models.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        ArrayList<Carros> listaCarros = new ArrayList<>();
        CarregarArquivos carregarArquivos = new CarregarArquivos();
        SalvarArquivos salvarArquivos = new SalvarArquivos(listaFuncionarios,listaCarros,listaClientes);

        TelaCadastroCarro telaCadastroCarro = new TelaCadastroCarro(salvarArquivos,listaCarros);


        carregarArquivos.carregarArquivos();
        listaFuncionarios = carregarArquivos.getListaFuncionarios();
        listaClientes = carregarArquivos.getListaClientes();
        listaCarros = carregarArquivos.getListaCarros();
        Locadora locadora = new Locadora(listaCarros,listaClientes);

        TelaLogin telaLogin = new TelaLogin(listaFuncionarios);
        boolean isAdmin = telaLogin.mostrarTelaLogin();

        TelaCadastroFuncionario telaCadastroFuncionario = new TelaCadastroFuncionario(listaFuncionarios);
        TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente(listaClientes, salvarArquivos);
        TelaMostrarFuncionarios telaMostrarFuncionarios = new TelaMostrarFuncionarios(listaFuncionarios, salvarArquivos);
        TelaMostrarCarros telaMostrarCarros = new TelaMostrarCarros(salvarArquivos,listaCarros, listaClientes, listaFuncionarios);
        TelaMostrarClientes telaMostrarClientes = new TelaMostrarClientes(listaClientes, salvarArquivos);
        TelaLucroTotal telaLucroTotal = new TelaLucroTotal(locadora);
        TelaRealizarAluguel telaRealizarAluguel = new TelaRealizarAluguel(salvarArquivos,listaCarros,listaClientes, listaFuncionarios);

        TelaAdmin telaAdmin = new TelaAdmin(listaFuncionarios, telaRealizarAluguel,telaLucroTotal, isAdmin, telaLogin, telaCadastroFuncionario, telaCadastroCliente, telaMostrarFuncionarios, telaMostrarClientes,telaMostrarCarros, telaCadastroCarro);
        telaAdmin.mostrarTelaAdmin();




    }
}
