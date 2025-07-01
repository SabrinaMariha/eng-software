package com.eng.soft.TrabalhoFinal.DAO;

import com.eng.soft.TrabalhoFinal.DTOs.ConsultaClientesDTO;
import com.eng.soft.TrabalhoFinal.model.CartaoDeCredito;
import com.eng.soft.TrabalhoFinal.model.Cliente;
import com.eng.soft.TrabalhoFinal.model.Endereco;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteDAO implements IDAO<Cliente> {

    private final DataSource dataSource;

    @Autowired
    public ClienteDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Transactional
    public void save(Cliente cliente) {
        String queryCliente = "INSERT INTO cliente (nome, data_de_nascimento, genero, cpf, email, tipo_de_telefone, telefone, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String queryEndereco = "INSERT INTO endereco (nome_pais, nome_estado, nome_cidade, tipo_de_residencia, tipo_de_logradouro, logradouro, numero, bairro, complemento, cep,  tipo_de_endereco, observacoes, cliente_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String queryCartao = "INSERT INTO cartao_de_credito (numero, bandeira, nome_titular, validade, cvv, cliente_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);


            long clienteId = saveCliente(connection, queryCliente, cliente);


            for (Endereco endereco : cliente.getEnderecos()) {
               saveEndereco(connection, queryEndereco, endereco, clienteId);
            }


            for (CartaoDeCredito cartao : cliente.getCartoesDeCredito()) {
                saveCartao(connection, queryCartao, cartao, clienteId);
            }


            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error saving Cliente: " + e.getMessage());
        }
    }

    private long saveCliente(Connection connection, String query, Cliente cliente) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getDataDeNascimento());
            preparedStatement.setString(3, cliente.getGenero()); 
            preparedStatement.setString(4, cliente.getCpf());   
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getTipoDeTelefone());
            preparedStatement.setString(7, cliente.getTelefone());
            preparedStatement.setString(8, cliente.getSenha());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1); 
                } else {
                    throw new SQLException("Failed to retrieve Cliente ID.");
                }
            }
        }
    }

    private long saveEndereco(Connection connection, String query, Endereco endereco, long clienteId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, endereco.getNomePais());
            preparedStatement.setString(2, endereco.getNomeEstado());
            preparedStatement.setString(3, endereco.getNomeCidade());
            preparedStatement.setString(4, endereco.getTipoDeResidencia());
            preparedStatement.setString(5, endereco.getTipoDeLogradouro());
            preparedStatement.setString(6, endereco.getLogradouro());
            preparedStatement.setString(7, endereco.getNumero());
            preparedStatement.setString(8, endereco.getBairro());
            preparedStatement.setString(9, endereco.getComplemento());
            preparedStatement.setString(10, endereco.getCep());
            preparedStatement.setString(11, endereco.getTipoDeEndereco());
            preparedStatement.setString(12, endereco.getObservacoes());
            preparedStatement.setLong(13, clienteId); // Set the cliente_id
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long enderecoId = generatedKeys.getLong(1);
                    endereco.setId(enderecoId); // Assign the generated ID to the Endereco object
                    return enderecoId;
                } else {
                    throw new SQLException("Failed to retrieve Endereco ID.");
                }
            }
        }
    }

    private long saveCartao(Connection connection, String query, CartaoDeCredito cartao, long clienteId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cartao.getBandeira());
            preparedStatement.setString(2, cartao.getNumero());
            preparedStatement.setString(3, cartao.getNomeTitular());
            preparedStatement.setString(4, cartao.getValidade());
            preparedStatement.setString(5, cartao.getCvv());
            preparedStatement.setLong(6, clienteId); // Set the cliente_id

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1); // Return the generated Cartao ID
                } else {
                    throw new SQLException("Failed to retrieve Cartao ID.");
                }
            }
        }
    }


    public List<Cliente> findAll(ConsultaClientesDTO consultaClientesDTO) {
        String queryCliente = "SELECT * FROM cliente WHERE " +
                "(? IS NULL OR LOWER(nome) LIKE LOWER(CONCAT('%', ?, '%'))) AND " +
                "(? IS NULL OR data_de_nascimento = ?) AND " +
                "(? IS NULL OR cpf = ?) AND " +
                "(? IS NULL OR LOWER(email) LIKE LOWER(CONCAT('%', ?, '%'))) AND " +
                "(? IS NULL OR telefone = ?)";

        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatementCliente = connection.prepareStatement(queryCliente)) {

            preparedStatementCliente.setString(1, consultaClientesDTO.nome());
            preparedStatementCliente.setString(2, consultaClientesDTO.nome());
            preparedStatementCliente.setString(3, consultaClientesDTO.dataDeNascimento());
            preparedStatementCliente.setString(4, consultaClientesDTO.dataDeNascimento());
            preparedStatementCliente.setString(5, consultaClientesDTO.cpf());
            preparedStatementCliente.setString(6, consultaClientesDTO.cpf());
            preparedStatementCliente.setString(7, consultaClientesDTO.email());
            preparedStatementCliente.setString(8, consultaClientesDTO.email());
            preparedStatementCliente.setString(9, consultaClientesDTO.telefone());
            preparedStatementCliente.setString(10, consultaClientesDTO.telefone());

            try (ResultSet resultSetCliente = preparedStatementCliente.executeQuery()) {
                while (resultSetCliente.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(resultSetCliente.getLong("id"));
                    cliente.setNome(resultSetCliente.getString("nome"));
                    cliente.setDataDeNascimento(resultSetCliente.getString("data_de_nascimento"));
                    cliente.setCpf(resultSetCliente.getString("cpf"));
                    cliente.setEmail(resultSetCliente.getString("email"));
                    cliente.setTipoDeTelefone(resultSetCliente.getString("tipo_de_telefone"));
                    cliente.setTelefone(resultSetCliente.getString("telefone"));
                    cliente.setSenha(resultSetCliente.getString("senha"));

                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao consultar clientes: " + e.getMessage());
        }

        return clientes;
    }

    public Cliente findById(Long id) throws SQLException {
        String queryCliente = "SELECT * FROM cliente WHERE id = ?";
        String queryCartoes = "SELECT * FROM cartao_de_credito WHERE cliente_id = ?";
        String queryEnderecos = "SELECT * FROM endereco WHERE cliente_id = ?";

        try (Connection connection = dataSource.getConnection()) {
            // Fetch the client
            Cliente cliente = null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryCliente)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        cliente = new Cliente();
                        cliente.setId(resultSet.getLong("id"));
                        cliente.setNome(resultSet.getString("nome"));
                        cliente.setDataDeNascimento(resultSet.getString("data_de_nascimento"));
                        cliente.setGenero(resultSet.getString("genero"));
                        cliente.setCpf(resultSet.getString("cpf"));
                        cliente.setEmail(resultSet.getString("email"));
                        cliente.setTelefone(resultSet.getString("telefone"));
                        cliente.setTipoDeTelefone(resultSet.getString("tipo_de_telefone"));
                        cliente.setSenha(resultSet.getString("senha"));
                    }
                }
            }

            if (cliente == null) {
                throw new RuntimeException("Cliente não encontrado com o ID: " + id);
            }

            // Fetch the credit cards
            List<CartaoDeCredito> cartoes = new ArrayList<>();
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryCartoes)) {
                preparedStatement.setLong(1, id); // Use the cliente_id
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        CartaoDeCredito cartao = new CartaoDeCredito();
                        cartao.setId(resultSet.getLong("id"));
                        cartao.setNumero(resultSet.getString("numero"));
                        cartao.setBandeira(resultSet.getString("bandeira"));
                        cartao.setNomeTitular(resultSet.getString("nome_titular"));
                        cartao.setValidade(resultSet.getString("validade"));
                        cartao.setCvv(resultSet.getString("cvv"));
                        cartao.setPreferencial(resultSet.getBoolean("preferencial"));
                        cartoes.add(cartao);
                    }
                }
            }
            cliente.setCartoesDeCredito(cartoes);

            // Fetch the addresses
            List<Endereco> enderecos = new ArrayList<>();
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryEnderecos)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Endereco endereco = new Endereco();
                        endereco.setId(resultSet.getLong("id"));
                        endereco.setNomePais(resultSet.getString("nome_pais"));
                        endereco.setNomeEstado(resultSet.getString("nome_estado"));
                        endereco.setNomeCidade(resultSet.getString("nome_cidade"));
                        endereco.setTipoDeResidencia(resultSet.getString("tipo_de_residencia"));
                        endereco.setTipoDeLogradouro(resultSet.getString("tipo_de_logradouro"));
                        endereco.setLogradouro(resultSet.getString("logradouro"));
                        endereco.setNumero(resultSet.getString("numero"));
                        endereco.setBairro(resultSet.getString("bairro"));
                        endereco.setComplemento(resultSet.getString("complemento"));
                        endereco.setCep(resultSet.getString("cep"));
                        endereco.setTipoDeEndereco(resultSet.getString("tipo_de_endereco"));
                        endereco.setObservacoes(resultSet.getString("observacoes"));

                        enderecos.add(endereco);
                    }
                }
            }
            cliente.setEnderecos(enderecos);

            return cliente;
        }
    }

    public void update(Cliente clienteExistente) {
        String queryCliente = "UPDATE cliente SET nome = ?, data_de_nascimento = ?, genero = ?, cpf = ?, email = ?, tipo_de_telefone = ?, telefone = ?, senha = ? WHERE id = ?";
        String queryEnderecoUpdate = "UPDATE endereco SET nome_pais = ?, nome_estado = ?, nome_cidade = ?, tipo_de_residencia = ?, tipo_de_logradouro = ?, logradouro = ?, numero = ?, bairro = ?, complemento = ?, cep = ?,  tipo_de_endereco = ?, observacoes = ? WHERE id = ? AND cliente_id = ?";
        String queryEnderecoInsert = "INSERT INTO endereco (nome_pais, nome_estado, nome_cidade, tipo_de_residencia, tipo_de_logradouro, logradouro, numero, bairro, complemento, cep,  tipo_de_endereco, observacoes, cliente_id) VALUES (?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String queryCartaoUpdate = "UPDATE cartao_de_credito SET numero = ?, bandeira = ?, nome_titular = ?, validade = ?, cvv = ?, preferencial = ? WHERE id = ? AND cliente_id = ?";
        String queryCartaoInsert = "INSERT INTO cartao_de_credito (numero, bandeira, nome_titular, validade, cvv, cliente_id, preferencial) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(queryCliente)) {
                preparedStatement.setString(1, clienteExistente.getNome());
                preparedStatement.setString(2, clienteExistente.getDataDeNascimento());
                preparedStatement.setString(3, clienteExistente.getGenero());
                preparedStatement.setString(4, clienteExistente.getCpf());
                preparedStatement.setString(5, clienteExistente.getEmail());
                preparedStatement.setString(6, clienteExistente.getTipoDeTelefone());
                preparedStatement.setString(7, clienteExistente.getTelefone());
                preparedStatement.setString(8, clienteExistente.getSenha()); // Ensure this is set
                preparedStatement.setLong(9, clienteExistente.getId()); // Set the ID
                preparedStatement.executeUpdate();
            }

            for (Endereco endereco : clienteExistente.getEnderecos()) {
                if (endereco.getId() == null) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(queryEnderecoInsert)) {
                        preparedStatement.setString(1, endereco.getNomePais());
                        preparedStatement.setString(2, endereco.getNomeEstado());
                        preparedStatement.setString(3, endereco.getNomeCidade());
                        preparedStatement.setString(4, endereco.getTipoDeResidencia());
                        preparedStatement.setString(5, endereco.getTipoDeLogradouro());
                        preparedStatement.setString(6, endereco.getLogradouro());
                        preparedStatement.setString(7, endereco.getNumero());
                        preparedStatement.setString(8, endereco.getBairro());
                        preparedStatement.setString(9, endereco.getComplemento());
                        preparedStatement.setString(10, endereco.getCep());
                        preparedStatement.setString(11, endereco.getTipoDeEndereco());
                        preparedStatement.setString(12, endereco.getObservacoes());
                        preparedStatement.setLong(13, clienteExistente.getId());
                        preparedStatement.executeUpdate();
                    }
                } else {
                    // Update existing address
                    try (PreparedStatement preparedStatement = connection.prepareStatement(queryEnderecoUpdate)) {
                        preparedStatement.setString(1, endereco.getNomePais());
                        preparedStatement.setString(2, endereco.getNomeEstado());
                        preparedStatement.setString(3, endereco.getNomeCidade());
                        preparedStatement.setString(4, endereco.getTipoDeResidencia());
                        preparedStatement.setString(5, endereco.getTipoDeLogradouro());
                        preparedStatement.setString(6, endereco.getLogradouro());
                        preparedStatement.setString(7, endereco.getNumero());
                        preparedStatement.setString(8, endereco.getBairro());
                        preparedStatement.setString(9, endereco.getComplemento());
                        preparedStatement.setString(10, endereco.getCep());
                        preparedStatement.setString(11, endereco.getTipoDeEndereco());
                        preparedStatement.setString(12, endereco.getObservacoes());
                        preparedStatement.setLong(13, endereco.getId());
                        preparedStatement.setLong(14, clienteExistente.getId());
                        preparedStatement.executeUpdate();
                    }
                }
            }

            // Update or insert credit cards
            for (CartaoDeCredito cartao : clienteExistente.getCartoesDeCredito()) {
                if (cartao.getId() == null) {
                    // Insert new credit card
                    try (PreparedStatement preparedStatement = connection.prepareStatement(queryCartaoInsert)) {
                        preparedStatement.setString(1, cartao.getNumero());
                        preparedStatement.setString(2, cartao.getBandeira());
                        preparedStatement.setString(3, cartao.getNomeTitular());
                        preparedStatement.setString(4, cartao.getValidade());
                        preparedStatement.setString(5, cartao.getCvv());
                        preparedStatement.setLong(6, clienteExistente.getId());
                        preparedStatement.setBoolean(7, cartao.getPreferencial()); // Ensure this is set
                        preparedStatement.executeUpdate();
                    }
                } else {
                    // Update existing credit card
                    try (PreparedStatement preparedStatement = connection.prepareStatement(queryCartaoUpdate)) {
                        preparedStatement.setString(1, cartao.getNumero());
                        preparedStatement.setString(2, cartao.getBandeira());
                        preparedStatement.setString(3, cartao.getNomeTitular());
                        preparedStatement.setString(4, cartao.getValidade());
                        preparedStatement.setString(5, cartao.getCvv());
                        preparedStatement.setBoolean(6, cartao.getPreferencial());
                        preparedStatement.setLong(7, cartao.getId());
                        preparedStatement.setLong(8, clienteExistente.getId());
                        preparedStatement.executeUpdate();
                    }
                }
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao editar cliente: " + e.getMessage());
        }
    }

}