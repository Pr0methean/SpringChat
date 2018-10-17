package com.userRepository.infrastructure;


import com.userRepository.applications.dto.NewUserDTO;
import com.userRepository.applications.dto.UserDTO;
import com.userRepository.applications.exceptions.ErrorDuringSaveUserException;
import com.userRepository.applications.exceptions.RepositorySQLException;
import com.userRepository.applications.exceptions.UserNotExistException;
import com.userRepository.domain.model.User;
import com.userRepository.domain.ports.UserRepository;

import javax.sql.DataSource;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserRepositoryMySQL implements UserRepository {


    private DataSource dataSource;


    public UserRepositoryMySQL(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Override
    public int saveUser(NewUserDTO userNew) throws ErrorDuringSaveUserException, RepositorySQLException {

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
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositorySQLException("Insert Query Error: " + e.getMessage());
        }

        throw new ErrorDuringSaveUserException("User was not save correct");
    }

    @Override
    public UserDTO fetchUserBy(int id) throws UserNotExistException, RepositorySQLException {

        try (Connection connection = this.dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id=" + id);

            if (resultSet.next()) {
                String nick = resultSet.getString("nick");
                return new UserDTO(id, nick);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositorySQLException("Select Query Error: " + e.getMessage());
        }
        throw new UserNotExistException("Not find user by id: " + id);
    }


    @Override
    public NewUserDTO fetchNewUserBy(int id) throws UserPrincipalNotFoundException, RepositorySQLException {

        try (Connection connection = this.dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id=" + id);

            if (resultSet.next()) {
                String nick = resultSet.getString("nick");
                String pass = resultSet.getString("pass");
                return new NewUserDTO(id, nick, pass);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositorySQLException("Select Query Error: " + e.getMessage());
        }
        throw new UserPrincipalNotFoundException("Not find user by id: " + id);
    }

    @Override
    public boolean userExistBy(String nick) throws RepositorySQLException {
        try (Connection connection = this.dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nick= \"" + nick + "\"");

            if (resultSet.first()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositorySQLException("Select Query Error: "+e.getMessage());
        }
    }

//    @Override
//    public UserDTO fetchUserBy(String nick, String password) throws RepositorySQLException, UserNotExistException {
//        try (Connection connection = this.dataSource.getConnection()) {
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nick=" + "\"" + nick + "\"" + " AND " + "pass=" + "\"" + password + "\"");
//            if (resultSet.next()) {
//
//                int id = resultSet.getInt("id");
//                return new UserDTO(id, nick);
//
//            }else {
//                throw new UserNotExistException("Not Find user");
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RepositorySQLException("Select Query Error: "+e.getMessage());
//        }
//
//    }

    @Override
    public List<UserDTO> fetchAllUsers() throws RepositorySQLException {

        try (Connection connection = this.dataSource.getConnection()) {

            List<UserDTO> users = new ArrayList<>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nick = resultSet.getString("nick");
                users.add(new UserDTO(id, nick));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositorySQLException("Select Query Error: "+e.getMessage());
        }
    }

    @Override
    public void deleteUserBy(int id) throws RepositorySQLException {

        try (Connection connection = this.dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM users WHERE id=\"" + id + "\"");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositorySQLException("Delete Query Error: "+e.getMessage());
        }

    }

    @Override
    public void updateUser(NewUserDTO userNew) throws RepositorySQLException {

        try (Connection connection = this.dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            statement.execute("UPDATE users SET nick=\"" + userNew.getNick() + "\", pass=\"" + userNew.getPassword() + "\" WHERE id=\"" + userNew.getId() + "\"");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositorySQLException("Update Query Error: "+e.getMessage());
        }
    }

    @Override
    public UserDTO fetchUserBy(String nick, String password) throws UserNotExistException {

//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(dataSource);
//        String sql = "SELECT * FROM users WHERE nick= ? AND pass = ?";
//        Integer integer = jdbcTemplate.queryForObject(sql, new Object[]{nick, password}, Integer.class);


        try(Connection connection = this.dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE nick= ? AND pass = ?");

            preparedStatement.setString(1, nick);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();


//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE nick=" + "\"" + nick + "\"" + " AND " + "pass=" + "\"" + password + "\"");
            resultSet.next();
            int id = resultSet.getInt("id");
            return new UserDTO(id, nick);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserNotExistException("Incorrect user or password");
        }

    }

}
