
package com.eng.soft.TrabalhoFinal.negocio.impl;
import com.eng.soft.TrabalhoFinal.model.DomainEntity;
import com.eng.soft.TrabalhoFinal.validacoes.IStrategy;




public class ValidarDadosObrigatoriosCartoes implements IStrategy<Cliente>  {

    @Override
    public String processar(Cliente cliente) {

        for (Cartao cartao : cliente.getCartoes()) {
            if (cartao.getNumero() == null || cartao.getNumero().isEmpty()) {
                return "O número do cartão é obrigatório.";
            }
            if (cartao.getNomeImpresso() == null || cartao.getNomeImpresso().isEmpty()) {
                return "O nome impresso no cartão é obrigatório.";
            }
            if (cartao.getBandeira() == null || cartao.getBandeira().isEmpty()) {
                return "A bandeira do cartão é obrigatória.";
            }
            if (cartao.getCodigoSeguranca() == null || cartao.getCodigoSeguranca().isEmpty()) {
                return "O código de segurança do cartão é obrigatório.";
            }
        }

        return "";
    }
}
