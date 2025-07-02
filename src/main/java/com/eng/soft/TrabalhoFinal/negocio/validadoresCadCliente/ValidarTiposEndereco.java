package com.eng.soft.TrabalhoFinal.negocio.validadoresCadCliente;
import com.eng.soft.TrabalhoFinal.model.Cliente;
import com.eng.soft.TrabalhoFinal.model.Endereco;
import com.eng.soft.TrabalhoFinal.negocio.IStrategy;



public class ValidarTiposEndereco implements IStrategy<Cliente> {

    @Override
    public String processar(Cliente cliente) {
        for (Endereco endereco : cliente.getEnderecos()) {
            if (endereco.getCobranca() == null && endereco.getEntrega() == null) {
                return "Todo cliente deve ter pelo menos um endereço de cobrança.";
            }
            if (endereco.getEntrega() == null && endereco.getCobranca() == null) {
                return "Todo cliente deve ter pelo menos um endereço de entrega.";
            }
        }
        return null;
    }
}
