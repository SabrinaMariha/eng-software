package com.eng.soft.TrabalhoFinal.controller;
import com.eng.soft.TrabalhoFinal.DAO.ClienteDAO;
import com.eng.soft.TrabalhoFinal.model.DomainEntity;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.eng.soft.TrabalhoFinal.DAO.IDAO;
import com.eng.soft.TrabalhoFinal.model.Cliente;
import com.eng.soft.TrabalhoFinal.validacoes.IStrategy;
import com.eng.soft.TrabalhoFinal.negocio.impl.*;
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

public class FachadaCliente implements IFachada {
    //responsável por chamar uma lista de strategys
    //e executar as regras de negócio
    Map<String, List<IStrategy>> rns = new HashMap<String, List<IStrategy>>();
	
	List<IStrategy> regrasCliente = new ArrayList<IStrategy>();

//    regrasCliente.add(new ValidarDadosObrigatoriosCadastro());
//    regrasCliente.add(new  ValidarTelefone());
//    regrasCliente.add(new ValidarDadosObrigatoriosCartoes());
//    regrasCliente.add(new ValidarBandeiraCartao());
//    regrasCliente.add(new ValidarDadosObrigatoriosEnderecos());
//    regrasCliente.add(new ValidarTiposEndereco());
//
//    rns.put(Cliente.class.getName(), regrasCliente);
    Map <String, IDAO> daos = new HashMap<String, IDAO>();
    //adicionar o DAO do Cliente no mapa de DAOs
    // Exemplo:
//    daos.put(Cliente.class.getName(), new ClienteDAO());
    ClienteDAO clienteDAO;

    public FachadaCliente(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
        regrasCliente.add(new ValidarDadosObrigatoriosCadastro());
        regrasCliente.add(new  ValidarTelefone());
        regrasCliente.add(new ValidarDadosObrigatoriosCartoes());
        regrasCliente.add(new ValidarBandeiraCartao());
        regrasCliente.add(new ValidarDadosObrigatoriosEnderecos());
        regrasCliente.add(new ValidarTiposEndereco());

        rns.put(Cliente.class.getName(), regrasCliente);

        // Adicionar o DAO do Cliente no mapa de DAOs
        daos.put(Cliente.class.getName(), clienteDAO);
    }



    @Override
    public String save(DomainEntity entity) {
        Cliente cliente = (Cliente) entity;

        String nmClasse = cliente.getClass().getName();
        List<IStrategy> rn = rns.get(nmClasse);
        StringBuilder sb = new StringBuilder();

        for(IStrategy s:rn) {
            String msg = s.processar(cliente);
            if(msg != null) {
                sb.append(msg);
            }
        }
        if(sb.length()==0) {
            IDAO dao = daos.get(nmClasse);
            dao.save(cliente);
        }
        return null;
    }

    @Override
    public String update(DomainEntity entity) {
        return "";
    }

    @Override
    public String delete(DomainEntity entity) {
        return "";
    }

    @Override
    public List<DomainEntity> findAll(DomainEntity entity) {
        Cliente clienteASerConsultado = (Cliente) entity;
        List<Cliente> clientes = clienteDAO.findAll(clienteASerConsultado);
        List<DomainEntity> domainEntities = new ArrayList<>();
        for (Cliente cliente : clientes) {
            domainEntities.add(cliente);
        }
        return domainEntities;
    }

    @Override
    public DomainEntity findById(Long id, Class<? extends DomainEntity> entityClass) {
        return null;
    }
}
