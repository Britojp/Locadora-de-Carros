package models;

import javax.swing.*;

public abstract class Pessoa {
    protected String nome;
    protected String CPF;
    protected short idade;

   public Pessoa(String nome, String CPF, short idade){
        this.nome = nome;
        this.CPF = CPF;
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }

    public void setIdade(short idade) {
        this.idade = idade;
    }

    public short getIdade() {
        return idade;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getCPF() {
        return CPF;
    }
}

