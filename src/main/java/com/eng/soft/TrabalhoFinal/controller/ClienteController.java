package com.eng.soft.TrabalhoFinal.controller;

import com.eng.soft.TrabalhoFinal.DAO.ClienteDAO;
import com.eng.soft.TrabalhoFinal.DTOs.DadosCadastroUsuario;
import com.eng.soft.TrabalhoFinal.model.Cliente;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public Map<String, String> cadastrarCliente(@RequestBody DadosCadastroUsuario clienteDados) {
        System.out.println("Recebido: " + clienteDados);
        var Cliente = new Cliente(clienteDados);
        clienteDAO.salvar(Cliente);

        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Cliente cadastrado com sucesso!");
        resposta.put("nome", Cliente.getNome()); // se tiver o método getNome()
        return resposta;
    }

}
