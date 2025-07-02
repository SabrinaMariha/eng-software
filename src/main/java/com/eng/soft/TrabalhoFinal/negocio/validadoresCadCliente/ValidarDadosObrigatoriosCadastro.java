
package com.eng.soft.TrabalhoFinal.negocio.validadoresCadCliente;
import com.eng.soft.TrabalhoFinal.model.Cliente;
import com.eng.soft.TrabalhoFinal.negocio.IStrategy;

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
        if (cliente.getDataDeNascimento() == null) {
            return "A data de nascimento do cliente é obrigatória.";
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            return "O telefone do cliente é obrigatório.";
        }
        //endereços é uma lista
        if (cliente.getEnderecos() == null || cliente.getEnderecos().isEmpty()) {
            return "O cliente deve ter pelo menos um endereço cadastrado.";
        }
        if(cliente.getCartoesDeCredito()!= null || !cliente.getCartoesDeCredito().isEmpty()) {
            new ValidarDadosObrigatoriosCartoes().processar(cliente);
        }
        new ValidarDadosObrigatoriosEnderecos().processar(cliente);
        return null;
    }
}