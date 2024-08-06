package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import arqs.SalvarArquivos;
import models.Aluguel;
import models.Carros;
import models.Cliente;
import models.Funcionario;

public class TelaRealizarAluguel {
    private ArrayList<Carros> listaCarros;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Funcionario> listaFuncionarios;
    private SalvarArquivos salvarArquivos;

    public TelaRealizarAluguel(SalvarArquivos salvarArquivos, ArrayList<Carros> listaCarros, ArrayList<Cliente> listaClientes, ArrayList<Funcionario> listaFuncionarios) {
        this.listaCarros = listaCarros;
        this.listaClientes = listaClientes;
        this.listaFuncionarios = listaFuncionarios;
        this.salvarArquivos = salvarArquivos;
    }

    public void criarTela(ArrayList<Funcionario> listaFuncionario) {
        JFrame frame = new JFrame("Realizar Aluguel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(5, 2, 5, 2));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel carroLabel = new JLabel("Carro:");
        JComboBox<Carros> comboCarros = new JComboBox<>(listaCarros.toArray(new Carros[0]));
        frame.add(carroLabel);
        frame.add(comboCarros);

        JLabel vendedorLabel = new JLabel("Vendedor:");
        JComboBox<Funcionario> comboVendedores = new JComboBox<>(listaFuncionarios.toArray(new Funcionario[0]));
        frame.add(vendedorLabel);
        frame.add(comboVendedores);

        JLabel clienteLabel = new JLabel("Cliente:");
        JComboBox<Cliente> comboClientes = new JComboBox<>(listaClientes.toArray(new Cliente[0]));
        frame.add(clienteLabel);
        frame.add(comboClientes);

        JLabel tempoLabel = new JLabel("Tempo de aluguel (dias):");
        JTextField tempoField = new JTextField();
        frame.add(tempoLabel);
        frame.add(tempoField);

        JButton realizarAluguelButton = new JButton("Realizar Aluguel");
        frame.add(realizarAluguelButton);

        JButton voltarButton = new JButton("Voltar");
        frame.add(voltarButton);

        realizarAluguelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarAluguel((Carros) comboCarros.getSelectedItem(), (Cliente) comboClientes.getSelectedItem(),
                        (Funcionario) comboVendedores.getSelectedItem(), tempoField.getText());
                        frame.setVisible(false);

            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        frame.setVisible(true);
    }

    private void realizarAluguel(Carros carro, Cliente cliente, Funcionario vendedor, String tempoStr) {
        try {
            if (carro.getAluguel().isAlugado()) {
                JOptionPane.showMessageDialog(null, "O carro selecionado já está alugado",
                        "Erro de Aluguel", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int tempoAluguel = Integer.parseInt(tempoStr);
            Aluguel aluguel = new Aluguel(carro.getValorCarro(), cliente, vendedor, tempoAluguel);
            carro.setAluguel(aluguel);
            carro.getAluguel().setTempoAluguel(tempoAluguel);
            carro.getAluguel().setAlugado(true);
            cliente.addCarro(carro);
            carro.getAluguel().setCliente(cliente);
            carro.getAluguel().setFuncionario(vendedor);

            JOptionPane.showMessageDialog(null, "Aluguel realizado com sucesso!");


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Formato de tempo inválido. Por favor, insira um número inteiro.",
                    "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }

    }
}
