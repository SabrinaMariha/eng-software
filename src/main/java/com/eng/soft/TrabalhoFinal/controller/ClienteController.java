package com.eng.soft.TrabalhoFinal.controller;

import com.eng.soft.TrabalhoFinal.DAO.ClienteDAO;
import com.eng.soft.TrabalhoFinal.DTOs.CadastroUsuarioDTO;
import com.eng.soft.TrabalhoFinal.DTOs.ClienteDTO;
import com.eng.soft.TrabalhoFinal.DTOs.ConsultaClientesDTO;
import com.eng.soft.TrabalhoFinal.model.Cliente;
import com.eng.soft.TrabalhoFinal.validacoes.Fachada;
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

    Fachada fachada = new Fachada();
    ClienteDAO clienteDAO;
    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @PostMapping("/")
    @Transactional
    public Map<String, String> cadastrarCliente(@RequestBody CadastroUsuarioDTO clienteDados) {
        System.out.println("Recebido: " + clienteDados);
        Cliente cliente = new Cliente(clienteDados);
        // Aqui você pode adicionar validações adicionais, se necessário
        // Por exemplo, verificar se o CPF já está cadastrado, validar a idade, etc.

        fachada.processar(cliente); // Executa as regras de negócio definidas nas estratégias
        clienteDAO.save(cliente);

        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Cliente cadastrado com sucesso!");
        resposta.put("nome", cliente.getNome()); // se tiver o método getNome()
        return resposta;
    }

    @GetMapping("/consulta")
    public ResponseEntity<Map<String, Object>> consultarClientes(ConsultaClientesDTO consultaClientesDTO) {
        System.out.println("Consulta recebida: " + consultaClientesDTO);

        List<Cliente> clientes = clienteDAO.findAll(consultaClientesDTO);
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
        Cliente cliente = clienteDAO.findById(id);

        // Map Cliente to ClienteDTO
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setDataDeNascimento(cliente.getDataDeNascimento());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setTipoDeTelefone(cliente.getTipoDeTelefone());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEnderecos(cliente.getEnderecos());
        clienteDTO.setCartoesDeCredito(cliente.getCartoesDeCredito());
        clienteDTO.setSenha(cliente.getSenha()); // Se necessário, adicione o campo senha

        return ResponseEntity.ok(clienteDTO);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/editar")
    @Transactional
    public ResponseEntity<Map<String, String>> atualizarCliente(@RequestParam Long id, @RequestBody CadastroUsuarioDTO clienteDados) throws SQLException {
        System.out.println("Atualizando cliente com ID: " + id);
        System.out.println("Dados recebidos: " + clienteDados);

        Cliente clienteExistente = clienteDAO.findById(id);
        if (clienteExistente == null) {
            return ResponseEntity.status(404).body(Map.of("mensagem", "Cliente não encontrado."));
        }

        // Atualiza os dados do cliente existente
        clienteExistente.editar(clienteDados);
        clienteDAO.update(clienteExistente);

        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Cliente atualizado com sucesso!");
        return ResponseEntity.ok(resposta);
    }

}
