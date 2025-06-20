package com.eng.soft.TrabalhoFinal.DTOs;


public record DadosCadastroUsuario(
    String nome,
    String dataDeNascimento,
    String cpf,
    String email,
    String tipoDeTelefone,
    String telefone,
    String senha
) {
}
