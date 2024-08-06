package telas;

import models.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TelaLogin {
    private ArrayList<Funcionario> listaFuncionarios;

    public TelaLogin(ArrayList<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public boolean mostrarTelaLogin() {
        JTextField usernameField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);

        passwordField.setEchoChar('*');

        JPanel janelaLogin = new JPanel(new GridLayout(2, 2, 5, 5));
        janelaLogin.add(new JLabel("Usuário:"));
        janelaLogin.add(usernameField);
        janelaLogin.add(new JLabel("Senha:"));
        janelaLogin.add(passwordField);

        boolean isAdmin = false;
        boolean autenticado = false;

        while (!autenticado) {
            int result = JOptionPane.showConfirmDialog(null, janelaLogin,
                    "Sessão de login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon("C:\\Users\\João Pedro\\Downloads\\LocadoraCarro\\LocadoraCarro\\src\\images\\icons8-person-80.png"));


            if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }

            if (result == JOptionPane.OK_OPTION) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                for (Funcionario funcionario : listaFuncionarios) {
                    Credenciais credenciais = funcionario.getCredenciais();
                    if (credenciais.getNomeUsuario().equals(username) && Arrays.equals(credenciais.getSenha().toCharArray(), password)) {
                        UserSession.getInstance().setFuncionario(funcionario);
                        isAdmin = credenciais.isAdmin();
                        autenticado = true;
                        break;
                    }
                }

                if (autenticado) {
                    String mensagem = isAdmin ? "Logado com sucesso como Gerente" : "Logado com sucesso como Vendedor";
                    JOptionPane.showMessageDialog(null, mensagem);
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciais inválidas");
                }

                Arrays.fill(password, ' ');
            }
        }

        return isAdmin;
    }

}
