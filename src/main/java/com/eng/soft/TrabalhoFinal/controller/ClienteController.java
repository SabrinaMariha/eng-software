package com.eng.soft.TrabalhoFinal.controller;

import com.eng.soft.TrabalhoFinal.DAO.ClienteDAO;
import com.eng.soft.TrabalhoFinal.DTOs.CadastroUsuarioDTO;
import com.eng.soft.TrabalhoFinal.DTOs.ClienteDTO;
import com.eng.soft.TrabalhoFinal.DTOs.ConsultaClientesDTO;
import com.eng.soft.TrabalhoFinal.model.Cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ClienteController {

    FachadaCliente fachada ;
    private  ClienteDAO clienteDAO;

    public ClienteController(ClienteDAO clienteDAO) {
        this.fachada = new FachadaCliente(clienteDAO);
        this.clienteDAO = clienteDAO;

    }

    @PostMapping("/")
    @Transactional
    public ResponseEntity<Map<String, Object>> cadastrarCliente(@RequestBody CadastroUsuarioDTO clienteDados) {
        System.out.println("Recebido: " + clienteDados);
        Cliente cliente = new Cliente(clienteDados);
        
        fachada.save(cliente); // Executa as regras de negócio definidas nas estratégias
       
        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Cliente cadastrado com sucesso!");
        resposta.put("nome", cliente.getNome()); // se tiver o método getNome()
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/consulta")
    public ResponseEntity<Map<String, Object>> consultarClientes(ConsultaClientesDTO consultaClientesDTO) {
        System.out.println("Consulta recebida: " + consultaClientesDTO);

        Cliente clienteASerConsultado = new Cliente(consultaClientesDTO);

        List<Cliente> clientes = fachada.findAll(clienteASerConsultado);
        Map<String, Object> resposta = new HashMap<>();

        if (clientes.isEmpty()) {
            resposta.put("mensagem", "Nenhum cliente encontrado.");
        } else {
            resposta.put("clientes", clientes);
        }

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/editar")
    public ResponseEntity<ClienteDTO> editarCliente(@RequestParam Long id) throws SQLException {
       // Cliente cliente = clienteDAO.findById(id);
        Cliente cliente = fachada.findById(id); 
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.update(cliente);
        return ResponseEntity.ok(clienteDTO);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/editar")
    @Transactional
    public ResponseEntity<Map<String, String>> atualizarCliente(@RequestParam Long id, @RequestBody CadastroUsuarioDTO clienteDados) throws SQLException {
        System.out.println("Atualizando cliente com ID: " + id);
        System.out.println("Dados recebidos: " + clienteDados);

        //Cliente clienteExistente = clienteDAO.findById(id);
        Cliente clienteExistente = fachada.findById(id);
        if (clienteExistente == null) {
            return ResponseEntity.status(404).body(Map.of("mensagem", "Cliente não encontrado."));
        }

        // Atualiza os dados do cliente existente
        clienteExistente.editar(clienteDados);
        fachada.update(clienteExistente);

        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Cliente atualizado com sucesso!");
        return ResponseEntity.ok(resposta);
    }

}
