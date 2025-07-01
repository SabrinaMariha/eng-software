
package com.eng.soft.TrabalhoFinal.negocio.impl;
import com.eng.soft.TrabalhoFinal.model.Cliente;
import com.eng.soft.TrabalhoFinal.model.DomainEntity;
import com.eng.soft.TrabalhoFinal.validacoes.IStrategy;
// Para todo cliente cadastrado é obrigatório o registro de ao menos um endereço de cobrança. 
// Para todo cliente cadastrado é obrigatório o registro de ao menos um endereço de entrega. 
// Todo cadastro de endereços associados a clientes deve ser composto dos seguintes
// dados: Tipo de residência (Casa, Apartamento, etc), Tipo Logradouro, Logradouro, Número, 
// Bairro, CEP, Cidade, Estado e País. Todos os campos anteriores são de preenchimento obrigatório.
// Opcionalmente pode ser preenchido um campo observações.
// Todo cartão de crédito associado a um cliente deverá ser composto pelos seguintes campos: 
// Nº do Cartão, Nome impresso no Cartão, Bandeira do Cartão e Código de Segurança.
// Todo cartão de crédito associado a um cliente deverá ser de alguma bandeira registrada no sistema.
// Para todo cliente cadastrado é obrigatório o cadastro dos seguintes dados:
// Gênero, Nome, Data de Nascimento, CPF, 
// Telefone (deve ser composto pelo tipo, DDD e número), e-mail, senha, endereço residencial.

public class ValidarTelefone implements IStrategy<Cliente>  {

    @Override
    public String processar(Cliente cliente) {
    
        if (cliente.getTelefone().length() < 10 || cliente.getTelefone().length()
                > 15) {
            return "O telefone do cliente deve ter entre 10 e 15 caracteres.";
        }
        if (!cliente.getTelefone().matches("\\d+")) {
            return "O telefone do cliente deve conter apenas números.";
        }
        if (cliente.getTipoDeTelefone() == null || cliente.getTipoDeTelefone().isEmpty()) {
            return "O tipo de telefone do cliente é obrigatório.";
        }

        return "";
    }
}
