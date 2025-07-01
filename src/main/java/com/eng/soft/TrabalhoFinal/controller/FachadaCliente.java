package com.eng.soft.TrabalhoFinal.controller;
import com.eng.soft.TrabalhoFinal.DAO.ClienteDAO;
import com.eng.soft.TrabalhoFinal.model.DomainEntity;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.eng.soft.TrabalhoFinal.DAO.IDAO;
import com.eng.soft.TrabalhoFinal.model.Cliente;
import com.eng.soft.TrabalhoFinal.negocio.*;


public class FachadaCliente implements IFachada<Cliente> {
    //responsável por chamar uma lista de strategys
    //e executar as regras de negócio
    Map<String, List<IStrategy>> rns = new HashMap<String, List<IStrategy>>();
	
	List<IStrategy> regrasCliente = new ArrayList<IStrategy>();

    Map <String, IDAO> daos = new HashMap<String, IDAO>();

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

       
        daos.put(Cliente.class.getName(), clienteDAO);
    }



    @Override
    public String save(Cliente cliente) {
        
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
    public String update(Cliente cliente) {
        return "";
    }

    @Override
    public String delete(Cliente cliente) {
        return "";
    }

    @Override
    public List<Cliente> findAll(Cliente clienteASerConsultado) {
        List<Cliente> clientes = clienteDAO.findAll(clienteASerConsultado);
        return clientes;
    }

    @Override
    public Cliente findById(Long id)  throws SQLException {
        return clienteDAO.findById(id);
    }
}
