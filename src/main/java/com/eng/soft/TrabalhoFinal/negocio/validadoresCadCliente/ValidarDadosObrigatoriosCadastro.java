
package com.eng.soft.TrabalhoFinal.negocio.impl;
import com.eng.soft.TrabalhoFinal.model.DomainEntity;
import com.eng.soft.TrabalhoFinal.validacoes.IStrategy;

public class ValidarDadosObrigatoriosCadastro implements IStrategy<Cliente>  {

    @Override
    public String processar(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            return "O nome do cliente é obrigatório.";
        }
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            return "O email do cliente é obrigatório.";
        }
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            return "O CPF do cliente é obrigatório.";
        }
        if (cliente.getDataNascimento() == null) {
            return "A data de nascimento do cliente é obrigatória.";
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            return "O telefone do cliente é obrigatório.";
        }
        if (cliente.getEnderecos() == null || cliente.getEnderecos().isEmpty()) {
            return "Pelo menos um endereço do cliente é obrigatório.";
        }
        if (cliente.getCartoes() == null || cliente.getCartoes().isEmpty()) {
            return "Pelo menos um cartão do cliente é obrigatório.";
        return null;
    }
}
