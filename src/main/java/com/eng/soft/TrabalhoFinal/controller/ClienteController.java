package com.eng.soft.TrabalhoFinal.controller;

import com.eng.soft.TrabalhoFinal.DAO.ClienteDAO;
import com.eng.soft.TrabalhoFinal.DTOs.DadosCadastroUsuario;
import com.eng.soft.TrabalhoFinal.entities.Cliente;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ClienteController {


    ClienteDAO clienteDAO;
    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @PostMapping("/")
    @Transactional
    public String cadastrarCliente(@RequestBody DadosCadastroUsuario clienteDados) {
        var Cliente = new Cliente(clienteDados);
        clienteDAO.salvar(Cliente);

        return "Cliente cadastrado com sucesso!";
    }

}
