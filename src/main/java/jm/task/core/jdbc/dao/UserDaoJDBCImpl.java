package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {

        try (Statement statement = connection.createStatement()) {
            String sql = """
                    CREATE TABLE IF NOT EXISTS Users(
                        Id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        Name VARCHAR(45),
                        LastName VARCHAR(45),
                        Age TINYINT)
                    """;
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE if exists Users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users (name, LastName, Age) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from users where Id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {

        String sql = " TRUNCATE TABLE users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
