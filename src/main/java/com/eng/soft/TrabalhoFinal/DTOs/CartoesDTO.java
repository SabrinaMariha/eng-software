package com.eng.soft.TrabalhoFinal.DTOs;
/*
CadastroClientes.js:119 {
  "nome": "luiz",
  "dataDeNascimento": "2025-06-05",
  "cpf": "546484",
  "email": "sasaaaab@gmai.ss",
  "tipoDeTelefone": "Residencial",
  "telefone": "5545",
  "senha": "g",
  "confirmacaoSenha": "g",
  "enderecos": [
    {
      "nomePais": "27",
      "nomeEstado": "2724",
      "nomeCidade": "27251",
      "tipoDeResidencia": "Casa",
      "tipoDeLogradouro": "Rua",
      "logradouro": "sa",
      "numero": "52",
      "bairro": "sa",
      "complemento": "",
      "cep": "",
      "cobranca": true,
      "observacoes": "sa"
    }
  ],
  "cartoesDeCredito": [
    {
      "numero": "4554",
      "nomeTitular": "sa",
      "validade": "",
      "cvv": "45"
    }
  ]
}
*/
public record CartoesDTO(
    Long id,
    String numero,
    String bandeira,
    String nomeTitular,
    String validade,
    String cvv,
    Boolean preferencial
) {
}
