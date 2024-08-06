package models;


import javax.swing.*;

public class Credenciais {
    private String nomeUsuario;
    private String senha;
    private boolean isAdmin;

    public Credenciais(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public Credenciais(JTextField usuario, JTextField senha) {

    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Nome de Usuário: " + nomeUsuario + "\nSenha: " + senha;
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}

