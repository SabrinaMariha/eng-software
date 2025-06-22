package com.eng.soft.TrabalhoFinal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;


    private String nomeCidade;
    private String nomeEstado;
    private String nomePais;
    private String tipoDeResidencia;
    private String tipoDeLogradouro;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String tipoDeEndereco;
    private boolean cobranca;
    private String observacoes;

    public Endereco() {}

    public Endereco(String tipoDeEndereco, String nomeCidade, String nomeEstado, String nomePais, String tipoDeResidencia, String tipoDeLogradouro, String logradouro, String numero, String complemento, String bairro, String cep, boolean cobranca, String observacoes) {
        this.tipoDeEndereco = tipoDeEndereco;
        this.tipoDeResidencia = tipoDeResidencia;
        this.tipoDeLogradouro = tipoDeLogradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cobranca = cobranca;
        this.observacoes = observacoes;
        this.nomeCidade = nomeCidade;
        this.nomeEstado = nomeEstado;
        this.nomePais = nomePais;

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public String getTipoDeEndereco() {
        return tipoDeEndereco;
    }
    public void setTipoDeEndereco(String tipoDeEndereco) {
        this.tipoDeEndereco = tipoDeEndereco;
    }
    public String getNomeCidade() {
        return nomeCidade;
    }
    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }
    public String getNomeEstado() {
        return nomeEstado;
    }
    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }
    public String getNomePais() {
        return nomePais;
    }
    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getTipoDeResidencia() {
        return tipoDeResidencia;
    }
    public void setTipoDeResidencia(String tipoDeResidencia) {
        this.tipoDeResidencia = tipoDeResidencia;
    }
    public String getTipoDeLogradouro() {
        return tipoDeLogradouro;
    }
    public void setTipoDeLogradouro(String tipoDeLogradouro) {
        this.tipoDeLogradouro = tipoDeLogradouro;
    }


    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean isCobranca() {
        return cobranca;
    }

    public void setCobranca(boolean cobranca) {
        this.cobranca = cobranca;
    }


    public void setId(long aLong) {
    }
}
