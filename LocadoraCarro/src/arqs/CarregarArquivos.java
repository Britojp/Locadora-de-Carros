package arqs;

import models.*;

import java.io.*;
import java.util.ArrayList;

public class CarregarArquivos {
    private ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
    private ArrayList<Carros> listaCarros = new ArrayList<>();
    private ArrayList<Cliente> listaClientes = new ArrayList<>();

    public void carregarArquivos() {
        carregarFuncionarios();
        carregarClientes();
        carregarCarros();
    }

    private void carregarFuncionarios() {
        try (BufferedReader buffRead = new BufferedReader(new FileReader("C:\\Users\\João Pedro\\Downloads\\LocadoraCarro\\LocadoraCarro\\src\\arqs\\funcionarios.txt"))) {
            String linha;
            while ((linha = buffRead.readLine()) != null) {
                String[] campos = linha.split("#");
                if (campos.length < 7) {
                    System.out.println("Formato de linha inválido: " + linha);
                    continue;
                }

                String nome = campos[0];
                String cpf = campos[1];
                int idade = Integer.parseInt(campos[2]);
                double salario = Double.parseDouble(campos[3]);
                String cargo = campos[4];
                String usuario = campos[5];
                String senha = campos[6];

                Credenciais credenciais = new Credenciais(usuario, senha);
                Funcionario funcionario;

                if (cargo.equals("Gerente")) {
                    funcionario = new Gerente(nome, cpf, (short) idade, salario, credenciais);
                } else {
                    funcionario = new Vendedor(nome, cpf, (short) idade, salario, credenciais);
                }

                listaFuncionarios.add(funcionario);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de funcionários: " + e.getMessage());
        }
    }

    private void carregarClientes() {
        try (BufferedReader buffRead = new BufferedReader(new FileReader("C:\\Users\\João Pedro\\Downloads\\LocadoraCarro\\LocadoraCarro\\src\\arqs\\clientes.txt"))) {
            String linha;
            while ((linha = buffRead.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos.length < 4) {
                    System.out.println("Formato de linha inválido: " + linha);
                    continue;
                }

                String nome = campos[0];
                String cpf = campos[1];
                int idade = Integer.parseInt(campos[2]);
                String numHabilitacao = campos[3];

                Cliente cliente = new Cliente(nome, cpf, (short) idade, numHabilitacao);
                listaClientes.add(cliente);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de clientes: " + e.getMessage());
        }
    }

    private void carregarCarros() {
        try (BufferedReader buffRead = new BufferedReader(new FileReader("C:\\Users\\João Pedro\\Downloads\\LocadoraCarro\\LocadoraCarro\\src\\arqs\\carros.txt"))) {
            String linha;
            while ((linha = buffRead.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos.length == 5) {
                    String placa = campos[0];
                    double valorCarro = Double.parseDouble(campos[1]);
                    boolean alugado = Boolean.parseBoolean(campos[2]);
                    String nomeCarro = campos[3];
                    String corCarro = campos[4];

                    Aluguel aluguel = new Aluguel(valorCarro, null, null, 0);
                    aluguel.setAlugado(alugado);

                    Carros carro = new Carros(placa, aluguel, valorCarro, nomeCarro, corCarro);
                    listaCarros.add(carro);
                } else if (campos.length == 8) {
                    String placa = campos[0];
                    double valorCarro = Double.parseDouble(campos[1]);
                    boolean alugado = Boolean.parseBoolean(campos[2]);
                    String nomeCarro = campos[3];
                    String corCarro = campos[4];
                    String nomeCliente = campos[5];
                    String nomeVendedor = campos[6];
                    int tempoAluguel = Integer.parseInt(campos[7]);

                    Cliente clienteEncontrado = null;
                    Funcionario funcionarioEncontrado = null;

                    for (Cliente cliente : listaClientes) {
                        if (cliente.getNome().equals(nomeCliente)) {
                            clienteEncontrado = cliente;
                            break;
                        }
                    }

                    for (Funcionario funcionario : listaFuncionarios) {
                        if (funcionario.getNome().equals(nomeVendedor)) {
                            funcionarioEncontrado = funcionario;
                            break;
                        }
                    }

                    if (clienteEncontrado == null || funcionarioEncontrado == null) {
                        System.out.println("Cliente ou funcionário não encontrado para o carro com placa: " + placa);
                        continue;
                    }

                    Aluguel aluguel = new Aluguel(valorCarro, clienteEncontrado, funcionarioEncontrado, tempoAluguel);
                    aluguel.setAlugado(alugado);

                    Carros carro = new Carros(placa, aluguel, valorCarro, nomeCarro, corCarro);
                    listaCarros.add(carro);
                    clienteEncontrado.addCarro(carro);
                    funcionarioEncontrado.addCarro(carro);
                } else {
                    System.out.println("Formato de linha inválido: " + linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de carros: " + e.getMessage());
        }
    }

    public ArrayList<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public ArrayList<Carros> getListaCarros() {
        return listaCarros;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
}
