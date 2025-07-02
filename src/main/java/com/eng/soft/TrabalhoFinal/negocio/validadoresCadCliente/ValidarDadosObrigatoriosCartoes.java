package com.eng.soft.TrabalhoFinal.negocio.validadoresCadCliente;

import com.eng.soft.TrabalhoFinal.model.CartaoDeCredito;
import com.eng.soft.TrabalhoFinal.model.Cliente;
import com.eng.soft.TrabalhoFinal.negocio.IStrategy;




public class ValidarDadosObrigatoriosCartoes implements IStrategy<Cliente>  {

    @Override
    public String processar(Cliente cliente) {

        for (CartaoDeCredito cartao : cliente.getCartoesDeCredito()) {
            if (cartao.getNumero() == null || cartao.getNumero().isEmpty()) {
                return "O número do cartão é obrigatório.";
            }
            if (cartao.getNomeTitular() == null || cartao.getNomeTitular().isEmpty()) {
                return "O nome impresso no cartão é obrigatório.";
            }
            if (cartao.getBandeira() == null || cartao.getBandeira().isEmpty()) {
                return "A bandeira do cartão é obrigatória.";
            }
            if (cartao.getCvv() == null || cartao.getCvv().isEmpty()) {
                return "O código de segurança do cartão é obrigatório.";
            }
        }
        new ValidarBandeiraCartao().processar(cliente);

        return null;
    }
}
