package com.eng.soft.TrabalhoFinal.DTOs;

import java.util.List;


public record DadosCadastroUsuario(
    String nome,
    String dataDeNascimento,
    String cpf,
    String email,
    String tipoDeTelefone,
    String telefone,
    String senha,
    String confirmacaoSenha,
    List<EnderecoDTO> enderecos,
    List<CartoesDTO> cartoesDeCredito

) {}
