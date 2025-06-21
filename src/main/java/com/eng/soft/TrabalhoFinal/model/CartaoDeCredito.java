// src/main/java/com/eng/soft/TrabalhoFinal/model/CartaoDeCredito.java
package com.eng.soft.TrabalhoFinal.model;

import jakarta.persistence.*;

@Entity
public class CartaoDeCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String bandeira;
    private String nomeTitular;
    private String validade;
    private String cvv;

    public CartaoDeCredito() {}

    public CartaoDeCredito(String bandeira, String numero, String nomeTitular, String validade, String cvv) {
        this.numero = numero;
        this.bandeira = bandeira;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
        this.cvv = cvv;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBandeira() { return bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = bandeira; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }
    public String getValidade() { return validade; }
    public void setValidade(String validade) { this.validade = validade; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
}