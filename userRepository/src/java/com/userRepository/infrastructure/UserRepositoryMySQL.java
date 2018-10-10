package com.userRepository.infrastructure;


import com.userRepository.applications.dto.NewUserDTO;
import com.userRepository.applications.dto.UserDTO;
import com.userRepository.domain.ports.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserRepositoryMySQL implements UserRepository {


    private DataSource dataSource;


    public UserRepositoryMySQL(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Override
    public int saveUser(NewUserDTO userNew) {

        try (Connection connection = this.dataSource.getConnection()) {
            String userName = userNew.getNick();
            String userPass = userNew.getPassword();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPass);
            preparedStatement.executeUpdate();

            ResultSet set = preparedStatement.getGeneratedKeys();
            if (set.next()) {
                return set.getInt(1);
            } else {
                return -1;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    @Override
    public UserDTO fetchUserBy(int id) {

        try (Connection connection = this.dataSource.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id=" + id);
            if (resultSet.next()) {
                String nick = resultSet.getString("nick");
                return new UserDTO(id, nick);
            } else {
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }


    @Override
    public NewUserDTO fetchUserNewBy(int id) {
        try (Connection connection = this.dataSource.getConnection()) {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id=" + id);
            if (resultSet.next()) {
                String nick = resultSet.getString("nick");
                String pass = resultSet.getString("pass");
                return new NewUserDTO(id, nick, pass);
            } else {

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean userExistBy(String nick) {
        try (Connection connection = this.dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nick=" + "\"" + nick + "\"");

            if (resultSet.first()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public UserDTO fetchUserBy(String nick, String password) {
        try (Connection connection = this.dataSource.getConnection()) {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nick=" + "\"" + nick + "\"" + " AND " + "pass=" + "\"" + password + "\"");
            resultSet.next();
            int id = resultSet.getInt("id");

            return new UserDTO(id, nick);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<UserDTO> fetchAllUsers() {

        try (Connection connection = this.dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            List<UserDTO> users = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String nick = resultSet.getString("nick");
                users.add(new UserDTO(id, nick));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUserBy(int id) {

        try (Connection connection = this.dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM users WHERE id=\"" + id + "\"");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateUser(NewUserDTO userNew) {
        try (Connection connection = this.dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            statement.execute("UPDATE users SET nick=\"" + userNew.getNick() + "\", pass=\"" + userNew.getPassword() + "\" WHERE id=\"" + userNew.getId() + "\"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
