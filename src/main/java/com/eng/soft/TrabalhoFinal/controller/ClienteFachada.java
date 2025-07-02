package com.eng.soft.TrabalhoFinal.controller;
import com.eng.soft.TrabalhoFinal.DAO.ClienteDAO;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.eng.soft.TrabalhoFinal.DAO.IDAO;
import com.eng.soft.TrabalhoFinal.model.Cliente;
import com.eng.soft.TrabalhoFinal.negocio.*;
import com.eng.soft.TrabalhoFinal.negocio.validadoresCadCliente.*;



public class ClienteFachada implements IFachada<Cliente> {
    //responsável por chamar uma lista de strategys
    //e executar as regras de negócio
    Map<String, List<IStrategy>> rns = new HashMap<String, List<IStrategy>>();
	
	List<IStrategy> regrasCliente = new ArrayList<IStrategy>();

    Map <String, IDAO> daos = new HashMap<String, IDAO>();

    ClienteDAO clienteDAO;



    public ClienteFachada (ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
        regrasCliente.add(new ValidarDadosObrigatoriosCadastro());
        regrasCliente.add(new  ValidarTelefone());

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
                sb.append("\n"+msg);
            }
        }
        if(sb.length()==0) {
            IDAO dao = daos.get(nmClasse);
            dao.save(cliente);
            return "Cliente cadastrado com sucesso!";
        }
        return sb.toString();
    }

    @Override
    public String update(Cliente cliente) {
        String nmClasse = cliente.getClass().getName();
        List<IStrategy> rn = rns.get(nmClasse);
        StringBuilder sb = new StringBuilder();

        for(IStrategy s:rn) {
            String msg = s.processar(cliente);
            if(msg != null) {
                sb.append("\n"+msg);
            }
        }
        if(sb.length()==0) {
            IDAO dao = daos.get(nmClasse);
            dao.update(cliente);
            return "Cliente atualizado com sucesso!";
        }
        return sb.toString();
    }
    @Override
    public String updateSenha(Cliente cliente) {
        String nmClasse = cliente.getClass().getName();
        List<IStrategy> rn = rns.get(nmClasse);
        StringBuilder sb = new StringBuilder();

        for(IStrategy s:rn) {
            String msg = s.processar(cliente);
            if(msg != null) {
                sb.append("\n"+msg);
            }
        }
        if(sb.length()==0) {
            IDAO dao = daos.get(nmClasse);
            dao.updateSenha(cliente);
            return "Senha atualizada com sucesso!";
        }
        return sb.toString();
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
