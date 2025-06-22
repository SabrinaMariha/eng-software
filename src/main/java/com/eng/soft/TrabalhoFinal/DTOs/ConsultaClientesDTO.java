package com.eng.soft.TrabalhoFinal.DTOs;

public record ConsultaClientesDTO(
    String nome,
    String dataDeNascimento,
    String cpf,
    String email,
    String telefone
) {
}
