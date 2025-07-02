
package com.eng.soft.TrabalhoFinal.negocio.validadoresCadCliente;
import com.eng.soft.TrabalhoFinal.model.Cliente;
import com.eng.soft.TrabalhoFinal.negocio.IStrategy;


public class ValidarBandeiraCartao implements IStrategy<Cliente> {
    

    @Override
    public String processar(Cliente cliente) {

        if(cliente.getCartoesDeCredito() != null || !cliente.getCartoesDeCredito().isEmpty()) {
            for (var cartao : cliente.getCartoesDeCredito()) {

                if (!cartao.getBandeira().matches("^(Visa|MasterCard|American Express|Discover|Diners Club|JCB)$")) {
                    return "A bandeira do cartão de crédito deve ser uma das seguintes: Visa, MasterCard, American Express, Discover, Diners Club ou JCB.";
                }
            }
        }

        return null;
    }
}
