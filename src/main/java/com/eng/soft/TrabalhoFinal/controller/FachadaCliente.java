package com.eng.soft.TrabalhoFinal.validacoes;
import com.eng.soft.TrabalhoFinal.model.DomainEntity;
import java.util.List;

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

public class Fachada implements IFachada {
    //responsável por chamar uma lista de strategys
    //e executar as regras de negócio
    rns = new HashMap<var, List<IStrategy>>();
	
	List<IStrategy> regrasCliente = new ArrayList<IStrategy>();

    regrasCliente.add(new ValidarDadosObrigatoriosCliente());
    regrasCliente.add(new ValidarTelefone());
    regrasCliente.add(new ValidarDadosObrigatoriosCartoes());
    regrasCliente.add(new ValidarBandeiraCartao());
    regrasCliente.add(new ValidarDadosObrigatoriosEnderecos());
    regrasCliente.add(new ValidarTiposEndereco());

    rns.put(Cliente.class.getName(), regrasCliente);
    daos = new HashMap<String, IDAO>();
	daos.put(Cliente.class.getName(), new ClienteDAO());

    @Override
    public String save(Cliente cliente) {
        String nmClasse = cliente.getClass().getName();
		List<IStrategy> rn = rns.get(nmClasse);
        StringBuilder sb = new StringBuilder();
        
        for(IStrategy s:rn) {
			String msg = s.processar(cliente);
			if(msg != null) {
				sb.append(msg)
			}
		}
        if(sb.length()==0) {
			IDAO dao = daos.get(nmClasse);
			dao.save(cliente);
		}
		return null;
    }
}
