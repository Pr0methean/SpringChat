package com.testUserInfrastructure;


import com.userRepository.applications.exceptions.ErrorDuringSaveUserException;
import com.userRepository.applications.exceptions.RepositorySQLException;
import com.userRepository.applications.exceptions.UserNotExistException;
import com.userRepository.domain.model.NewUser;
import com.userRepository.domain.model.User;
import com.userRepository.domain.ports.UserRepository;
import com.userRepository.infrastructure.UserRepositoryMySQL;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class UserTestMySql {

    private Logger log = LogManager.getLogger(String.valueOf(UserTestMySql.class));
    private DatabaseTestConfig databaseTestConfig;
    private UserRepository userRepository;

    public UserTestMySql() {
        this.databaseTestConfig = new DatabaseTestConfig();
        this.userRepository = new UserRepositoryMySQL(databaseTestConfig.getDataSource());
    }

    @BeforeEach
    public void beforeTest() {
        databaseTestConfig.dropTables();
        databaseTestConfig.createDefaultsTable();
    }

    @Test
    public void test() {
        log.info("Test info");
        log.debug("Test debug");
        log.error("Test error");
        log.fatal("Test fatal");

    }

    @Test
    public void shouldSaveNewUserAndCheckExistence() {

        try {
            //given
            NewUser newUser = new NewUser("John", "Pass");

            //when
            userRepository.saveUser(newUser);
            NewUser newUserFromMySQL = userRepository.fetchNewUserBy(1);

            //then
            assertEquals(1, newUserFromMySQL.getId());
            assertEquals(newUser.getNick(), newUserFromMySQL.getNick());
            assertEquals(newUser.getPassword(), newUserFromMySQL.getPassword());
            log.info("Success test : shouldSaveNewUserAndCheckExistence");
        } catch (ErrorDuringSaveUserException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (RepositorySQLException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (UserPrincipalNotFoundException e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    @Test
    public void shouldFetchUserBy() {
        try {
            //given
            NewUser newUser = new NewUser("John", "PassAA");
            NewUser newUser2 = new NewUser("Mark", "Pass");
            NewUser newUser3 = new NewUser("Paul", "Pass");

            //when
            userRepository.saveUser(newUser);
            userRepository.saveUser(newUser2);
            userRepository.saveUser(newUser3);

            NewUser newUserFromMySQL = userRepository.fetchNewUserBy(2);

            //then
            assertEquals(2, newUserFromMySQL.getId());
            log.info("Success test : shouldFetchUserBy");
        } catch (ErrorDuringSaveUserException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (RepositorySQLException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (UserPrincipalNotFoundException e) {
            e.printStackTrace();
            log.error(e.toString());
        }

    }

    @Test
    public void shouldFetchUserExistBy() {
        try {
            //given
            NewUser newUser = new NewUser("John", "PassAA");
            NewUser newUser2 = new NewUser("Mark", "Pass");


            //when
            userRepository.saveUser(newUser);
            userRepository.saveUser(newUser2);


            boolean excist = userRepository.userExistBy("John");
            boolean notExcist = userRepository.userExistBy("Paul");

            //then
            assertTrue(excist);
            assertFalse(notExcist);
            log.info("Success test : shouldFetchUserExistBy");
        } catch (ErrorDuringSaveUserException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (RepositorySQLException e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    @Test
    public void shouldFetchUserByNickAndPass() {
        try {
            //given
            NewUser newUser = new NewUser("John", "PassAA");
            NewUser newUser2 = new NewUser("Mark", "Pass");

            //when
            userRepository.saveUser(newUser);
            userRepository.saveUser(newUser2);

            //then
            User userExistence = userRepository.fetchUserBy("John", "PassAA");
            Executable code = () -> {
                User user = userRepository.fetchUserBy("John", "abcd");
            };
            assertThrows(UserNotExistException.class, code);
            assertEquals(newUser.getNick(), userExistence.getNick());
            log.info("Success test : shouldFetchUserByNickAndPass");
        } catch (ErrorDuringSaveUserException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (RepositorySQLException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (UserPrincipalNotFoundException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (UserNotExistException e) {
            e.printStackTrace();
            log.error(e.toString());
        }

    }

    @Test
    public void shouldFetchAllUsers() {
        try {
            //given
            NewUser newUser = new NewUser("John", "PassAA");
            NewUser newUser2 = new NewUser("Mark", "Pass");

            //when
            userRepository.saveUser(newUser);
            userRepository.saveUser(newUser2);
            List<User> userList = userRepository.fetchAllUsers();

            //then
            assertEquals(2, userList.size());
            log.info("Success test : shouldFetchAllUsers");
        } catch (ErrorDuringSaveUserException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (RepositorySQLException e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    @Test
    public void shouldDeleteUserById() {
        try {
            NewUser newUser = new NewUser("John", "PassAA");
            NewUser newUser2 = new NewUser("Mark", "Pass");


            //when
            userRepository.saveUser(newUser);
            userRepository.saveUser(newUser2);

            //then
            userRepository.deleteUserBy(2);

            //then
            Executable date = () -> {
                log.info(userRepository.fetchUserBy(2).toString());
            };
            User user = userRepository.fetchUserBy(1);
            assertThrows(UserNotExistException.class, date);
            assertNotNull(user);
            log.info("Success test : shouldDeleteMessageAndThrowExceptionNotFound");
        } catch (ErrorDuringSaveUserException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (RepositorySQLException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (UserNotExistException e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    @Test
    public void shouldUpdateUser() {
        try {
            //given
            NewUser newUser = new NewUser("John", "PassAA");
            NewUser userToUpdate = new NewUser("John", "NewPassword");

            //when
            userRepository.saveUser(newUser);
            userRepository.updateUser(userToUpdate);

            NewUser user = userRepository.fetchNewUserBy(100);

            //then
            assertEquals("NewPassword", (user.getPassword()));
            log.info("Success test : shouldUpdateUser");
        } catch (ErrorDuringSaveUserException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (RepositorySQLException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (UserPrincipalNotFoundException e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    @Test
    public void shouldFetchNewUserById() {
        try {
            //given
            NewUser newUser = new NewUser("John", "PassAA");
            NewUser userToUpdate = new NewUser("John", "NewPassword");

            //when
            userRepository.saveUser(newUser);
            NewUser user = userRepository.fetchNewUserBy(1);

            //then
            assertEquals("John", (user.getNick()));
            log.info("Success test : shouldUpdateUser");
        } catch (ErrorDuringSaveUserException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (RepositorySQLException e) {
            e.printStackTrace();
            log.error(e.toString());
        } catch (UserPrincipalNotFoundException e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }
}
