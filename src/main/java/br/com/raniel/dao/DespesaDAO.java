package br.com.raniel.dao;

import br.com.raniel.infra.ConnectionFactory;
import br.com.raniel.model.Categoria;
import br.com.raniel.model.Despesa;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DespesaDAO implements IDespesaDAO {
    @Override
    public Despesa save(Despesa despesa) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO despesas (descricao, data, valor, categoria) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, despesa.getDescricao());
            preparedStatement.setDate(2, java.sql.Date.valueOf(despesa.getData()));
            preparedStatement.setDouble(3, despesa.getValor());
            preparedStatement.setString(4, despesa.getCategoria().toString());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Long generatedId = resultSet.getLong("id");
            despesa.setId(generatedId);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return despesa;
    }

    @Override
    public Despesa update(Despesa despesa) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE despesas SET descricao = ?, valor = ?, data = ?, categoria = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, despesa.getDescricao());
            preparedStatement.setDouble(2, despesa.getValor());
            preparedStatement.setDate(3, java.sql.Date.valueOf(despesa.getData()));
            preparedStatement.setString(4, despesa.getCategoria().toString());
            preparedStatement.setLong(5, despesa.getId());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return despesa;
    }

    @Override
    public void delete(Long id) {
        try(Connection connection = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM despesas WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Despesa> findAll() {
        String sql = "SELECT id, descricao, data, valor, categoria FROM despesas";
        List<Despesa> despesas = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long id =resultSet.getLong("id");
                String descricao = resultSet.getString("descricao");
                LocalDate data = resultSet.getDate("data").toLocalDate();
                double valor = resultSet.getDouble("valor");
                Categoria categoria = Categoria.valueOf(resultSet.getString("categoria"));
                Despesa despesa = new Despesa(id, descricao, data,valor, categoria);
                despesas.add(despesa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return despesas;
    }

    @Override
    public Optional<Despesa> findById(Long id) {
        String sql = "SELECT id, descricao, data, valor, categoria FROM despesas WHERE id = ?";
        Despesa despesa = null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long pKey =resultSet.getLong("id");
                String descricao = resultSet.getString("descricao");
                LocalDate data = resultSet.getDate("data").toLocalDate();
                double valor = resultSet.getDouble("valor");
                Categoria categoria = Categoria.valueOf(resultSet.getString("categoria"));
                despesa = new Despesa(pKey, descricao, data,valor, categoria);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(despesa);
    }

    @Override
    public List<Despesa> findByCategoria(Categoria categoria) {
        String sql = "SELECT id, descricao, data, valor, categoria FROM despesas WHERE categoria = ?";
        List<Despesa> despesas = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoria.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long id =resultSet.getLong("id");
                String descricao = resultSet.getString("descricao");
                LocalDate data = resultSet.getDate("data").toLocalDate();
                double valor = resultSet.getDouble("valor");
                Categoria cat = Categoria.valueOf(resultSet.getString("categoria"));
                Despesa despesa = new Despesa(id, descricao, data,valor, cat);
                despesas.add(despesa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return despesas;
    }
}
