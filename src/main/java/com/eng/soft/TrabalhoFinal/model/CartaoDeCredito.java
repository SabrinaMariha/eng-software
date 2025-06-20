// src/main/java/com/eng/soft/TrabalhoFinal/model/CartaoDeCredito.java
package com.eng.soft.TrabalhoFinal.model;

import jakarta.persistence.*;

@Entity
public class CartaoDeCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String nomeTitular;
    private String validade;
    private String cvv;

    public CartaoDeCredito() {}

    public CartaoDeCredito(String numero, String nomeTitular, String validade, String cvv) {
        this.numero = numero;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
        this.cvv = cvv;
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }
    public String getValidade() { return validade; }
    public void setValidade(String validade) { this.validade = validade; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
}