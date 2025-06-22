package com.eng.soft.TrabalhoFinal.model;

import com.eng.soft.TrabalhoFinal.DTOs.CartoesDTO;
import com.eng.soft.TrabalhoFinal.DTOs.CadastroUsuarioDTO;
import com.eng.soft.TrabalhoFinal.DTOs.EnderecoDTO;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dataDeNascimento;
    private String cpf;
    private String email;
    private String tipoDeTelefone;
    private String telefone;
    private String senha;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartaoDeCredito> cartoesDeCredito = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente() {}

    public Cliente(CadastroUsuarioDTO clienteDados) {
        this();
        this.nome = clienteDados.nome();
        this.dataDeNascimento = clienteDados.dataDeNascimento();
        this.cpf = clienteDados.cpf();
        this.email = clienteDados.email();
        this.tipoDeTelefone = clienteDados.tipoDeTelefone();
        this.telefone = clienteDados.telefone();
        this.senha = clienteDados.senha();
        for (EnderecoDTO enderecoDTO : clienteDados.enderecos()) {
            Endereco endereco = new Endereco(
                    enderecoDTO.tipoDeResidencia(),
                    enderecoDTO.nomeCidade(),
                    enderecoDTO.nomeEstado(),
                    enderecoDTO.nomePais(),
                    enderecoDTO.tipoDeResidencia(),
                    enderecoDTO.tipoDeLogradouro(),
                    enderecoDTO.logradouro(),
                    enderecoDTO.numero(),
                    enderecoDTO.complemento(),
                    enderecoDTO.bairro(),
                    enderecoDTO.cep(),
                    enderecoDTO.cobranca(),
                    enderecoDTO.observacoes()
            );
            this.enderecos.add(endereco);
        }
        for (CartoesDTO cartaoDTO : clienteDados.cartoesDeCredito()) {
            CartaoDeCredito cartao = new CartaoDeCredito(
                    cartaoDTO.numero(),
                    cartaoDTO.bandeira(),
                    cartaoDTO.nomeTitular(),
                    cartaoDTO.validade(),
                    cartaoDTO.cvv()
            );
            this.cartoesDeCredito.add(cartao);
        }
    }

    // getters e setters...


    public Long getId() {
        return id;
    }
    public List<Endereco> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    public List<CartaoDeCredito> getCartoesDeCredito() {
        return cartoesDeCredito;
    }
    public void setCartoesDeCredito(List<CartaoDeCredito> cartoesDeCredito) {
        this.cartoesDeCredito = cartoesDeCredito;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDataDeNascimento() {
        return dataDeNascimento;
    }
    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTipoDeTelefone() {
        return tipoDeTelefone;
    }
    public void setTipoDeTelefone(String tipoDeTelefone) {
        this.tipoDeTelefone = tipoDeTelefone;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                //", endereco=" + endereco +
               // ", cartoesDeCredito=" + cartoesDeCredito +
                ", nome='" + nome + '\'' +
                ", dataDeNascimento='" + dataDeNascimento + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", tipoDeTelefone='" + tipoDeTelefone + '\'' +
                ", telefone='" + telefone + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }
}
