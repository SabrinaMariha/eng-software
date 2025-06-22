package com.eng.soft.TrabalhoFinal.controller;

import com.eng.soft.TrabalhoFinal.DAO.ClienteDAO;
import com.eng.soft.TrabalhoFinal.DTOs.CadastroUsuarioDTO;
import com.eng.soft.TrabalhoFinal.DTOs.ConsultaClientesDTO;
import com.eng.soft.TrabalhoFinal.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ClienteController {


    ClienteDAO clienteDAO;
    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @PostMapping("/")
    @Transactional
    public Map<String, String> cadastrarCliente(@RequestBody CadastroUsuarioDTO clienteDados) {
        System.out.println("Recebido: " + clienteDados);
        var Cliente = new Cliente(clienteDados);
        clienteDAO.salvar(Cliente);

        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Cliente cadastrado com sucesso!");
        resposta.put("nome", Cliente.getNome()); // se tiver o método getNome()
        return resposta;
    }

    @GetMapping("/consulta")
    public ResponseEntity<Map<String, Object>> consultarClientes(ConsultaClientesDTO consultaClientesDTO) {
        System.out.println("Consulta recebida: " + consultaClientesDTO);

        List<Cliente> clientes = clienteDAO.consultarClientes(consultaClientesDTO);
        Map<String, Object> resposta = new HashMap<>();

        if (clientes.isEmpty()) {
            resposta.put("mensagem", "Nenhum cliente encontrado.");
        } else {
            resposta.put("clientes", clientes);
        }

        return ResponseEntity.ok(resposta);
    }

}
