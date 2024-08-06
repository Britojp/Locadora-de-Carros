package telas;

import arqs.SalvarArquivos;
import models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaCadastroCarro {
    private ArrayList<Carros> listaCarros;
    private SalvarArquivos salvarArquivos;

    public TelaCadastroCarro(SalvarArquivos salvarArquivos, ArrayList<Carros> listaCarros) {
        this.listaCarros = listaCarros;
        this.salvarArquivos = salvarArquivos;
    }

    public void mostrarTelaCadastroCarro() {
        JFrame frame = new JFrame("Cadastro de carros");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel cadastroDeNovosCarros = new JLabel("Cadastro de novos carros");
        cadastroDeNovosCarros.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel orientacao = new JLabel("Preencha todos os campos abaixo");
        orientacao.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField placaCarro = new JTextField(10);
        JTextField valorCarro = new JTextField(10);
        JTextField nomeCarro = new JTextField(10);
        JTextField corCarro = new JTextField(10);

        JPanel janelaCadastroCarro = new JPanel(new GridLayout(10, 2, 5, 5));
        janelaCadastroCarro.add(new JLabel("Nome do carro:"));
        janelaCadastroCarro.add(nomeCarro);
        janelaCadastroCarro.add(new JLabel("Placa do carro:"));
        janelaCadastroCarro.add(placaCarro);
        janelaCadastroCarro.add(new JLabel("Valor do carro:"));
        janelaCadastroCarro.add(valorCarro);
        janelaCadastroCarro.add(new JLabel("Cor do carro:"));
        janelaCadastroCarro.add(corCarro);


        JButton cadastrarButton = new JButton("Cadastrar");
        JButton voltarButton = new JButton("Voltar");

        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        voltarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String placaDoCarro = placaCarro.getText();
                    double valorDoCarro = Double.parseDouble(valorCarro.getText());
                    String nomeDoCarro = nomeCarro.getText();
                    String corDoCarro = corCarro.getText();
                        Aluguel aluguel = new Aluguel();
                        Carros carro = new Carros(placaDoCarro, aluguel, valorDoCarro, nomeDoCarro, corDoCarro);

                    listaCarros.add(carro);
                    salvarArquivos.salvarCarros(listaCarros);
                    JOptionPane.showMessageDialog(frame, "Carro cadastrado com sucesso!");
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        panel.add(cadastroDeNovosCarros);
        panel.add(orientacao);
        panel.add(janelaCadastroCarro);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(cadastrarButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(voltarButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));



        frame.add(panel);
        frame.setVisible(true);
    }
}
