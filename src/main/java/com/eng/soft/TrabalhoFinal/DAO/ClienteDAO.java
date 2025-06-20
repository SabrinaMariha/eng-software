package com.eng.soft.TrabalhoFinal.DAO;

import com.eng.soft.TrabalhoFinal.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ClienteDAO {

    private final DataSource dataSource;

    @Autowired
    public ClienteDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void consultar(Cliente cliente) {
        String query = "SELECT * FROM cliente WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, cliente.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cliente.setNome(resultSet.getString("nome"));
                    cliente.setDataDeNascimento(resultSet.getString("data_de_nascimento"));
                    cliente.setCpf(resultSet.getString("cpf"));
                    cliente.setEmail(resultSet.getString("email"));
                    cliente.setTipoDeTelefone(resultSet.getString("tipo_de_telefone"));
                    cliente.setTelefone(resultSet.getString("telefone"));
                    cliente.setSenha(resultSet.getString("senha"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao consultar cliente: " + e.getMessage());
        }
    }

    public void salvar(Cliente cliente) {
        String query = "INSERT INTO cliente (nome, data_de_nascimento, cpf, email, tipo_de_telefone, telefone, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getDataDeNascimento());
            preparedStatement.setString(3, cliente.getCpf());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setString(5, cliente.getTipoDeTelefone());
            preparedStatement.setString(6, cliente.getTelefone());
            preparedStatement.setString(7, cliente.getSenha());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
}
