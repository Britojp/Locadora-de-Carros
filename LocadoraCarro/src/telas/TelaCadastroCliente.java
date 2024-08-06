package telas;

import arqs.SalvarArquivos;
import models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaCadastroCliente {
    private ArrayList<Cliente> listaCliente;
    private SalvarArquivos salvarArquivos;

    public TelaCadastroCliente(ArrayList<Cliente> listaCliente, SalvarArquivos salvarArquivos) {
        this.listaCliente = listaCliente;
        this.salvarArquivos = salvarArquivos;
    }

    public void mostrarTelaCadastroClientes() {

        JFrame frame = new JFrame("Cadastro de Clientes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel cadastroNovosClientes = new JLabel("Cadastro de novos cliente");
        cadastroNovosClientes.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel orientacao = new JLabel("Preencha todos os campos abaixo");
        orientacao.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nome = new JTextField(10);
        JTextField CPF = new JTextField(10);
        JTextField idade = new JTextField(10);
        JTextField numHabilitacao = new JTextField(10);

        JPanel janelaCadastroCliente = new JPanel(new GridLayout(10, 2, 5, 5));

        cadastroNovosClientes.setAlignmentX(Component.CENTER_ALIGNMENT);
        janelaCadastroCliente.add(new JLabel("Nome:"));
        janelaCadastroCliente.add(nome);
        janelaCadastroCliente.add(new JLabel("CPF:"));
        janelaCadastroCliente.add(CPF);
        janelaCadastroCliente.add(new JLabel("Idade:"));
        janelaCadastroCliente.add(idade);
        janelaCadastroCliente.add(new JLabel("Habilitação: :"));
        janelaCadastroCliente.add(numHabilitacao);


        JButton cadastrarButton = new JButton("Cadastrar");
        JButton voltarButton = new JButton("Voltar");

        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        voltarButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nomeCliente = nome.getText();
                    String cpfCliente = CPF.getText();
                    short idadeCliente = Short.parseShort(idade.getText());
                    String habilitacaoCliente = numHabilitacao.getText();

                    Cliente cliente = new Cliente(nomeCliente, cpfCliente, idadeCliente, habilitacaoCliente);
                    listaCliente.add(cliente);

                    JOptionPane.showMessageDialog(frame, "Cliente cadastrado com sucesso!");
                    salvarArquivos.salvarClientes(listaCliente);
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, preencha os campos corretamente.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Ocorreu um erro ao cadastrar o cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(cadastroNovosClientes);
        panel.add(orientacao);
        panel.add(janelaCadastroCliente);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(cadastrarButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(voltarButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));


        frame.getContentPane().add(panel);
        frame.setVisible(true);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);

            }
        });
    }
}
