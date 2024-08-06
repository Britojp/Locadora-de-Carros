package arqs;

import models.*;

import java.io.*;
import java.util.ArrayList;

public class SalvarArquivos {
    private ArrayList<Funcionario> listaFuncionarios;
    private ArrayList<Carros> listaCarros;
    private ArrayList<Cliente> listaClientes;

    public SalvarArquivos(ArrayList<Funcionario> listaFuncionarios, ArrayList<Carros> listaCarros, ArrayList<Cliente> listaClientes) {
        this.listaFuncionarios = listaFuncionarios;
        this.listaCarros = listaCarros;
        this.listaClientes = listaClientes;
    }



    public void salvarFuncionarios(ArrayList<Funcionario> listaFuncionarios) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\João Pedro\\Downloads\\LocadoraCarro\\LocadoraCarro\\src\\arqs\\funcionarios.txt"))) {
            for (Funcionario funcionario : listaFuncionarios) {
                String nome = funcionario.getNome();
                String CPF = funcionario.getCPF();
                short idade = funcionario.getIdade();
                double salario = funcionario.getSalario();
                String cargo = funcionario.getCargo();
                String nomeUsuario = funcionario.getCredenciais().getNomeUsuario();
                String senha = funcionario.getCredenciais().getSenha();

                bufferedWriter.write(String.format("%s#%s#%d#%.0f#%s#%s#%s%n", nome, CPF, idade, salario, cargo, nomeUsuario, senha));
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo de funcionários: " + e.getMessage());
        }
    }
    public void salvarClientes(ArrayList<Cliente> listaClientes){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\João Pedro\\Downloads\\LocadoraCarro\\LocadoraCarro\\src\\arqs\\clientes.txt"))) {
            for (Cliente cliente : listaClientes) {
                String nome = cliente.getNome();
                String CPF = cliente.getCPF();
                short idade = cliente.getIdade();
                String numhabilitacao = cliente.getNumHabilitacao();

                bufferedWriter.write(String.format("%s,%s,%d,%s%n", nome, CPF, idade,numhabilitacao));
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo de clientes: " + e.getMessage());
        }

    }
    public void salvarCarros(ArrayList<Carros> listaCarros){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\João Pedro\\Downloads\\LocadoraCarro\\LocadoraCarro\\src\\arqs\\carros.txt"))) {
            for (Carros carros : listaCarros) {
                String placaCarro = carros.getPlacaCarro();
                double valorCarro = carros.getValorCarro();
                String nomeCarro = carros.getNomeCarro();
                String corCarro = carros.getCorCarro();
                boolean alugadoCarro = carros.getAluguel().isAlugado();

                if(!alugadoCarro) {
                    bufferedWriter.write(String.format("%s,%s,%b,%s,%s%n", placaCarro, valorCarro, alugadoCarro, nomeCarro, corCarro));
                } else {
                    String nomeCliente = carros.getAluguel().getCliente().getNome();
                    String nomeVendedor = carros.getAluguel().getFuncionario().getNome();
                    int tempoAluguel = carros.getAluguel().getTempoAluguel();
                    bufferedWriter.write(String.format("%s,%s,%b,%s,%s,%s,%s,%d%n", placaCarro, valorCarro, alugadoCarro, nomeCarro, corCarro, nomeCliente, nomeVendedor, tempoAluguel));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo de carros: " + e.getMessage());
        }
    }
}