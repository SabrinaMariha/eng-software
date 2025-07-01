package com.eng.soft.TrabalhoFinal.DTOs;

import java.util.List;


public record CadastroUsuarioDTO(
    String nome,
    String dataDeNascimento,
    String genero,
    String cpf,
    String email,
    String tipoDeTelefone,
    String telefone,
    String senha,
    String confirmacaoSenha,
    List<EnderecoDTO> enderecos,
    List<CartoesDTO> cartoesDeCredito

) {

}
