package com.eng.soft.TrabalhoFinal.DTOs;

import com.eng.soft.TrabalhoFinal.model.CartaoDeCredito;
import com.eng.soft.TrabalhoFinal.model.Endereco;

import java.util.List;

public class ClienteDTO {
    private String nome;
    private String dataDeNascimento;
    private String genero;
    private String cpf;
    private String email;
    private String tipoDeTelefone;
    private String telefone;
    private String senha;
    private List<EnderecoDTO> enderecos;
    private List<CartoesDTO> cartoesDeCredito;


    // Getters and Setters
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getTipoDeTelefone() { return tipoDeTelefone; }
    public void setTipoDeTelefone(String tipoDeTelefone) { this.tipoDeTelefone = tipoDeTelefone; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDataDeNascimento() { return dataDeNascimento; }
    public void setDataDeNascimento(String dataDeNascimento) { this.dataDeNascimento = dataDeNascimento; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public List<EnderecoDTO> getEnderecos() { return enderecos; }
    public List<CartoesDTO> getCartoesDeCredito() { return cartoesDeCredito; }
    public void setEnderecos(List<Endereco> enderecos) {
        List<EnderecoDTO> enderecosDTO = enderecos.stream()
                .map(endereco -> new EnderecoDTO(
                        endereco.getId(),
                        endereco.getNomeCidade(),
                        endereco.getNomeEstado(),
                        endereco.getNomePais(),
                        endereco.getTipoDeResidencia(),
                        endereco.getTipoDeLogradouro(),
                        endereco.getTipoDeEndereco(),
                        endereco.getCobranca(),
                        endereco.getEntrega(),
                        endereco.getLogradouro(),
                        endereco.getNumero(),
                        endereco.getComplemento(),
                        endereco.getBairro(),
                        endereco.getCep(),
                        endereco.getObservacoes()
                ))
                .toList();
        this.enderecos = enderecosDTO;
    }
    public void setCartoesDeCredito(List<CartaoDeCredito> cartoesDeCredito) {
        List<CartoesDTO> cartoesDTO = cartoesDeCredito.stream()
                .map(cartao -> new CartoesDTO(
                        cartao.getId(),
                        cartao.getNumero(),
                        cartao.getBandeira(),
                        cartao.getNomeTitular(),
                        cartao.getValidade(),
                        cartao.getCvv(),
                        cartao.getPreferencial()
                ))
                .toList();
        this.cartoesDeCredito = cartoesDTO;
    }   

    public void update(ClienteDTO clienteDTO) {
        this.nome = clienteDTO.getNome();
        this.dataDeNascimento = clienteDTO.getDataDeNascimento();
        this.genero = clienteDTO.getGenero();
        this.cpf = clienteDTO.getCpf();
        this.email = clienteDTO.getEmail();
        this.tipoDeTelefone = clienteDTO.getTipoDeTelefone();
        this.telefone = clienteDTO.getTelefone();
        this.senha = clienteDTO.getSenha();
        this.enderecos = clienteDTO.getEnderecos();
        this.cartoesDeCredito = clienteDTO.getCartoesDeCredito();
    }
}