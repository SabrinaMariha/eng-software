package com.eng.soft.TrabalhoFinal.model;

import jakarta.persistence.*;

@Entity
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;

    // Getters e Setters
}
