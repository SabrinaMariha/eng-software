package com.eng.soft.TrabalhoFinal.model;

import com.eng.soft.TrabalhoFinal.DTOs.CartoesDTO;
import com.eng.soft.TrabalhoFinal.DTOs.CadastroUsuarioDTO;
import com.eng.soft.TrabalhoFinal.DTOs.ConsultaClientesDTO;
import com.eng.soft.TrabalhoFinal.DTOs.EnderecoDTO;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente extends DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dataDeNascimento;
    private String genero;
    private String cpf;
    private String email;
    private String tipoDeTelefone;
    private String telefone;
    private String senha;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<CartaoDeCredito> cartoesDeCredito = new ArrayList<>();

    public Cliente() {}

    public Cliente(CadastroUsuarioDTO clienteDados) {
        this();
        this.nome = clienteDados.nome();
        this.dataDeNascimento = clienteDados.dataDeNascimento();
        this.genero = clienteDados.genero();
        this.cpf = clienteDados.cpf();
        this.email = clienteDados.email();
        this.tipoDeTelefone = clienteDados.tipoDeTelefone();
        this.telefone = clienteDados.telefone();
        this.senha = clienteDados.senha();
        for (EnderecoDTO enderecoDTO : clienteDados.enderecos()) {
            Endereco endereco;
            if (enderecoDTO == null) {
                    endereco = new Endereco(
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
                    enderecoDTO.tipoDeEndereco(),
                    enderecoDTO.observacoes()
            );
            } else {
                        endereco = new Endereco(
                        enderecoDTO.id(),
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
                        enderecoDTO.tipoDeEndereco(),
                        enderecoDTO.observacoes()
                );
            }
            this.enderecos.add(endereco);
        }
        for (CartoesDTO cartaoDTO : clienteDados.cartoesDeCredito()) {
            CartaoDeCredito cartao;
            if (cartaoDTO == null) {
                    cartao = new CartaoDeCredito(
                    cartaoDTO.numero(),
                    cartaoDTO.bandeira(),
                    cartaoDTO.nomeTitular(),
                    cartaoDTO.validade(),
                    cartaoDTO.cvv(),
                    cartaoDTO.preferencial()
            );
            } else {
                cartao = new CartaoDeCredito(
                        cartaoDTO.id(),
                        cartaoDTO.numero(),
                        cartaoDTO.bandeira(),
                        cartaoDTO.nomeTitular(),
                        cartaoDTO.validade(),
                        cartaoDTO.cvv(),
                        cartaoDTO.preferencial()
                );
            }
            this.cartoesDeCredito.add(cartao);
        }
    }

    public Cliente(ConsultaClientesDTO consultaClientesDTO) {
        this.nome = consultaClientesDTO.nome();
        this.dataDeNascimento = consultaClientesDTO.dataDeNascimento();

        this.cpf = consultaClientesDTO.cpf();
        this.email = consultaClientesDTO.email();

        this.telefone = consultaClientesDTO.telefone();


        // Inicializa as listas de endereços e cartões de crédito
        this.enderecos = new ArrayList<>();
        this.cartoesDeCredito = new ArrayList<>();
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

    public void editar(CadastroUsuarioDTO clienteDados) {
        this.nome = clienteDados.nome();
        this.dataDeNascimento = clienteDados.dataDeNascimento();
        this.cpf = clienteDados.cpf();
        this.email = clienteDados.email();
        this.tipoDeTelefone = clienteDados.tipoDeTelefone();
        this.telefone = clienteDados.telefone();
        this.senha = clienteDados.senha();

        this.enderecos.clear();
        for (EnderecoDTO enderecoDTO : clienteDados.enderecos()) {
            Endereco endereco = enderecoDTO.id() == null
                    ? new Endereco() // Novo endereço
                    : this.enderecos.stream()
                    .filter(e -> e.getId().equals(enderecoDTO.id()))
                    .findFirst()
                    .orElse(new Endereco()); // Atualizar existente ou criar novo

            endereco.setNomeCidade(enderecoDTO.nomeCidade());
            endereco.setNomeEstado(enderecoDTO.nomeEstado());
            endereco.setNomePais(enderecoDTO.nomePais());
            endereco.setTipoDeResidencia(enderecoDTO.tipoDeResidencia());
            endereco.setTipoDeLogradouro(enderecoDTO.tipoDeLogradouro());
            endereco.setLogradouro(enderecoDTO.logradouro());
            endereco.setNumero(enderecoDTO.numero());
            endereco.setComplemento(enderecoDTO.complemento());
            endereco.setBairro(enderecoDTO.bairro());
            endereco.setCep(enderecoDTO.cep());
            endereco.setTipoDeEndereco(enderecoDTO.tipoDeEndereco());
            endereco.setObservacoes(enderecoDTO.observacoes());
            endereco.setCliente(this); // Vincula ao cliente

            this.enderecos.add(endereco);
        }


        // Atualiza ou adiciona cartões de crédito
        this.cartoesDeCredito.clear();
        for (CartoesDTO cartaoDTO : clienteDados.cartoesDeCredito()) {
            CartaoDeCredito cartao = cartaoDTO.id() == null
                    ? new CartaoDeCredito() // Novo cartão
                    : this.cartoesDeCredito.stream()
                    .filter(c -> c.getId().equals(cartaoDTO.id()))
                    .findFirst()
                    .orElse(new CartaoDeCredito()); // Atualizar existente ou criar novo

            cartao.setNumero(cartaoDTO.numero());
            cartao.setBandeira(cartaoDTO.bandeira());
            cartao.setNomeTitular(cartaoDTO.nomeTitular());
            cartao.setValidade(cartaoDTO.validade());
            cartao.setCvv(cartaoDTO.cvv());
            cartao.setPreferencial(cartaoDTO.preferencial());
            cartao.setCliente(this); // Vincula ao cliente

            this.cartoesDeCredito.add(cartao);
        }
    }

    public String getGenero() {
    return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
}
