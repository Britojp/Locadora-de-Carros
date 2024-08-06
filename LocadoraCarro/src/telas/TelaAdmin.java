package telas;

import models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaAdmin {
    private boolean isAdmin;
    private TelaLogin telaLogin;
    private TelaCadastroFuncionario telaCadastroFuncionario;
    private TelaCadastroCliente telaCadastroCliente;
    private TelaMostrarFuncionarios telaMostrarFuncionarios;
    private TelaMostrarClientes telaMostrarClientes;
    private TelaMostrarCarros telaMostrarCarros;
    private TelaCadastroCarro telaCadastroCarro;
    private TelaLucroTotal telaLucroTotal;
    private TelaRealizarAluguel telaRealizarAluguel;
    private ArrayList<Funcionario> listaFuncionario;

    public TelaAdmin(ArrayList<Funcionario> listaFuncionario,TelaRealizarAluguel telaRealizarAluguel,TelaLucroTotal lucroTotal, boolean isAdmin, TelaLogin telaLogin, TelaCadastroFuncionario telaCadastroFuncionario, TelaCadastroCliente telaCadastroCliente, TelaMostrarFuncionarios telaMostrarFuncionarios, TelaMostrarClientes telaMostrarClientes, TelaMostrarCarros telaMostrarCarros, TelaCadastroCarro telaCadastroCarro) {
        this.isAdmin = isAdmin;
        this.telaLogin = telaLogin;
        this.telaCadastroFuncionario = telaCadastroFuncionario;
        this.telaCadastroCliente = telaCadastroCliente;
        this.telaMostrarFuncionarios = telaMostrarFuncionarios;
        this.telaMostrarClientes = telaMostrarClientes;
        this.telaMostrarCarros = telaMostrarCarros;
        this.telaCadastroCarro = telaCadastroCarro;
        this.telaLucroTotal = lucroTotal;
        this.telaRealizarAluguel = telaRealizarAluguel;
        this.listaFuncionario = listaFuncionario;
    }
    public void mostrarTelaAdmin() {
        Funcionario funcionarioLogado = UserSession.getInstance().getFuncionario();
        JFrame adminFrame = new JFrame("Funcionário");
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setSize(600, 600);
        adminFrame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon;
        if (isAdmin) {
            icon = new ImageIcon("\\images\\icons8-manager-80.png");
        } else {
            icon = new ImageIcon("\\src\\images\\icons8-seller-80.png");
        }
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(imageLabel);

        JLabel bemVindo = new JLabel("Bem vindo, " + funcionarioLogado.getNome());
        bemVindo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel orientacao = new JLabel("Pressione o botão que possua a opção desejada");
        orientacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(bemVindo);
        panel.add(orientacao);

        JButton cadastrarCliente = new JButton("Cadastrar Cliente");
        JButton consultarCliente = new JButton("Consultar Cliente");
        JButton cadastrarFuncionario = new JButton("Cadastrar Funcionario");
        JButton consultarFuncionario = new JButton("Consultar Funcionario");
        JButton consultarLucroTotal = new JButton("Consultar Lucro Total");
        JButton cadastrarCarros = new JButton("Cadastrar Carros");
        JButton consultarCarros = new JButton("Consultar Carros");
        JButton alugarCarro = new JButton("Realizar aluguel");
        JButton sair = new JButton("Desconectar");

        cadastrarCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        consultarCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        cadastrarFuncionario.setAlignmentX(Component.CENTER_ALIGNMENT);
        consultarFuncionario.setAlignmentX(Component.CENTER_ALIGNMENT);
        consultarLucroTotal.setAlignmentX(Component.CENTER_ALIGNMENT);
        cadastrarCarros.setAlignmentX(Component.CENTER_ALIGNMENT);
        consultarCarros.setAlignmentX(Component.CENTER_ALIGNMENT);
        alugarCarro.setAlignmentX(Component.CENTER_ALIGNMENT);
        sair.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(cadastrarCliente);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(consultarCliente);

        cadastrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaCadastroCliente.mostrarTelaCadastroClientes();
            }
        });

        consultarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaMostrarClientes.mostrarTodosClientes();
            }
        });

        if (isAdmin) {
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(cadastrarFuncionario);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(consultarFuncionario);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(consultarLucroTotal);

            cadastrarFuncionario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    telaCadastroFuncionario.mostrarTelaCadastroFuncionario();
                }
            });

            consultarFuncionario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    telaMostrarFuncionarios.mostrarTodosFuncionarios();
                }
            });

            consultarLucroTotal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(adminFrame, "Consultar Lucro Total");
                    telaLucroTotal.mostrarLucroTotal();
                }
            });
        }

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(consultarCarros);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(cadastrarCarros);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(alugarCarro);



        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(sair);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            System.exit(0);
            }
        });


            cadastrarCarros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaCadastroCarro.mostrarTelaCadastroCarro();
            }
        });

        consultarCarros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaMostrarCarros.mostrarTodosCarros();
            }
        });

        alugarCarro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaRealizarAluguel.criarTela(listaFuncionario);
            }
        });

        adminFrame.getContentPane().add(panel);
        adminFrame.setVisible(true);

    }



}
