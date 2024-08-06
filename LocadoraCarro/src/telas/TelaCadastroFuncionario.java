package telas;

import arqs.SalvarArquivos;
import models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaCadastroFuncionario {
    private ArrayList<Funcionario> listaFuncionarios;
    private SalvarArquivos salvarArquivos;

    public TelaCadastroFuncionario(ArrayList<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public void mostrarTelaCadastroFuncionario() {

        JFrame frame = new JFrame("Cadastro de Funcionário");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel cadastroDeNovosFuncionarios = new JLabel("Cadastro de novos funcionários");
        cadastroDeNovosFuncionarios.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel orientacao = new JLabel("Preencha todos os campos abaixo");
        orientacao.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nome = new JTextField(10);
        JTextField CPF = new JTextField(10);
        JTextField idade = new JTextField(10);
        JTextField salario = new JTextField(10);
        JTextField usuario = new JTextField(10);
        JPasswordField senha = new JPasswordField(10);

        String[] tiposFuncionario = {"Gerente", "Vendedor"};
        JComboBox<String> tipoFuncionarioCombo = new JComboBox<>(tiposFuncionario);

        JPanel janelaCadastroFuncionario = new JPanel(new GridLayout(10, 2, 5, 5));
        janelaCadastroFuncionario.add(new JLabel("Tipo de Funcionário:"));
        janelaCadastroFuncionario.add(tipoFuncionarioCombo);
        janelaCadastroFuncionario.add(new JLabel("Nome:"));
        janelaCadastroFuncionario.add(nome);
        janelaCadastroFuncionario.add(new JLabel("CPF:"));
        janelaCadastroFuncionario.add(CPF);
        janelaCadastroFuncionario.add(new JLabel("Idade:"));
        janelaCadastroFuncionario.add(idade);
        janelaCadastroFuncionario.add(new JLabel("Salário:"));
        janelaCadastroFuncionario.add(salario);
        janelaCadastroFuncionario.add(new JLabel("Usuario:"));
        janelaCadastroFuncionario.add(usuario);
        janelaCadastroFuncionario.add(new JLabel("Senha:"));
        janelaCadastroFuncionario.add(senha);



        JButton cadastrarButton = new JButton("Cadastrar");
        JButton voltarButton = new JButton("Voltar");

        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        voltarButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nomeFuncionario = nome.getText();
                    String cpfFuncionario = CPF.getText();
                    short idadeFuncionario = Short.parseShort(idade.getText());
                    String tipoFuncionario = (String) tipoFuncionarioCombo.getSelectedItem();
                    double salarioFuncionario = Double.parseDouble(salario.getText());
                    String usuarioFuncionario = usuario.getText();
                    String senhaFuncionario = new String(senha.getPassword());

                    Credenciais credenciais = new Credenciais(usuarioFuncionario, senhaFuncionario);

                    if (tipoFuncionario.equals("Gerente")) {
                        Funcionario gerente = new Gerente(nomeFuncionario,cpfFuncionario, idadeFuncionario, salarioFuncionario, credenciais);
                        listaFuncionarios.add(gerente);

                    } else {
                        Funcionario vendedor = new Vendedor(nomeFuncionario, cpfFuncionario, idadeFuncionario, salarioFuncionario, credenciais);
                        listaFuncionarios.add(vendedor);

                    }
                    salvarArquivos.salvarFuncionarios(listaFuncionarios);
                    JOptionPane.showMessageDialog(frame, "Funcionário cadastrado com sucesso!");
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, preencha os campos numéricos corretamente.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Ocorreu um erro ao cadastrar o funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(cadastroDeNovosFuncionarios);
        panel.add(orientacao);
        panel.add(janelaCadastroFuncionario);
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
