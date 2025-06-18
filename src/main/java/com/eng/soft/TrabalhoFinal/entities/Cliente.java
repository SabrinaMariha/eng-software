package com.eng.soft.TrabalhoFinal.entities;

import jakarta.persistence.*;

import java.util.List;
/*
* Nome
Data de Nascimento
CPF
Email
Tipo de Telefone
Telefone
Senha
Endereço
Cartão de Credito
* */
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<CartaoDeCredito> cartoesDeCredito;
    private String nome;
    private String dataDeNascimento;
    private String cpf;
    private String email;
    private String tipoDeTelefone;
    private String telefone;
    private String senha;

    @Embedded
    private Endereco endereco;
    @ElementCollection
    @CollectionTable(name = "cartoes_de_credito", joinColumns = @JoinColumn(name = "cliente_id"))

    public Long getId() {
        return id;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public List<CartaoDeCredito> getCartoesDeCredito() {
        return cartoesDeCredito;
    }
    public void setCartoesDeCredito(List<CartaoDeCredito> cartoesDeCredito) {
        this.cartoesDeCredito = cartoesDeCredito;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDataDeNascimento() {
        return dataDeNascimento;
    }
    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTipoDeTelefone() {
        return tipoDeTelefone;
    }
    public void setTipoDeTelefone(String tipoDeTelefone) {
        this.tipoDeTelefone = tipoDeTelefone;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", endereco=" + endereco +
                ", cartoesDeCredito=" + cartoesDeCredito +
                ", nome='" + nome + '\'' +
                ", dataDeNascimento='" + dataDeNascimento + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", tipoDeTelefone='" + tipoDeTelefone + '\'' +
                ", telefone='" + telefone + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
