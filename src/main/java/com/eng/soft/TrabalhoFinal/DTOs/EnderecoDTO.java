package com.eng.soft.TrabalhoFinal.DTOs;
/*:3000/favicon.ico:1


            Failed to load resource: the server responded with a status of 404 (Not Found)
[NOVO] Explique os erros do Console usando o Copilot no Edge: clique em

         para explicar um erro.
        Saiba mais
        Não mostrar novamente
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
// EnderecoDTO.java
public record EnderecoDTO (
    String nomeCidade,
    String nomeEstado,
    String nomePais,
    String tipoDeResidencia,
    String tipoDeLogradouro,
    String logradouro,
    String numero,
    String complemento,
    String bairro,
    String cep,
    boolean cobranca,
    String observacoes
){

}