
package com.eng.soft.TrabalhoFinal.negocio.validadoresCadCliente;
import com.eng.soft.TrabalhoFinal.model.Cliente;
import com.eng.soft.TrabalhoFinal.model.Endereco;
import com.eng.soft.TrabalhoFinal.negocio.IStrategy;

// Todo cartão de crédito associado a um cliente deverá ser composto pelos seguintes campos: 
// Nº do Cartão, Nome impresso no Cartão, Bandeira do Cartão e Código de Segurança.
// Todo cartão de crédito associado a um cliente deverá ser de alguma bandeira registrada no sistema.
// Para todo cliente cadastrado é obrigatório o cadastro dos seguintes dados:
// Gênero, Nome, Data de Nascimento, CPF, 
// Telefone (deve ser composto pelo tipo, DDD e número), e-mail, senha, endereço residencial.
public class ValidarDadosObrigatoriosEnderecos implements IStrategy<Cliente>  {

    @Override
    public String processar(Cliente cliente) {
       
        for (Endereco endereco : cliente.getEnderecos()) {
            if (endereco.getTipoDeResidencia() == null || endereco.getTipoDeResidencia().isEmpty()) {
                return "O tipo de residência é obrigatório.";
            }
            if (endereco.getTipoDeLogradouro() == null || endereco.getTipoDeLogradouro().isEmpty()) {
                return "O tipo de logradouro é obrigatório.";
            }
            if (endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty()) {
                return "O logradouro é obrigatório.";
            }
            if (endereco.getNumero() == null || endereco.getNumero().isEmpty()) {
                return "O número é obrigatório.";
            }
            if (endereco.getBairro() == null || endereco.getBairro().isEmpty()) {
                return "O bairro é obrigatório.";
            }
            if (endereco.getCep() == null || endereco.getCep().isEmpty()) {
                return "O CEP é obrigatório.";
            }
            if (endereco.getNomeCidade() == null || endereco.getNomeCidade().isEmpty()) {
                return "A cidade é obrigatória.";
            }
            if (endereco.getNomeEstado() == null || endereco.getNomeEstado().isEmpty()) {
                return "O estado é obrigatório.";
            }
            if (endereco.getNomePais() == null || endereco.getNomePais().isEmpty()) {
                return "O país é obrigatório.";
            }
        }
        new ValidarTiposEndereco().processar(cliente);

        return null;
    }
}