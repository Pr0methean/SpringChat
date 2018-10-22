//package com.testUserInfrastructure;
//
//
//import com.userRepository.applications.dto.NewUserDTO;
//import com.userRepository.applications.dto.UserDTO;
//import com.userRepository.applications.exceptions.ErrorDuringSaveUserException;
//import com.userRepository.applications.exceptions.RepositorySQLException;
//import com.userRepository.applications.exceptions.UserNotExistException;
//import com.userRepository.domain.ports.UserRepository;
//import com.userRepository.infrastructure.UserRepositoryMySQL;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.nio.file.attribute.UserPrincipalNotFoundException;
//import java.util.List;
//
//public class UserTestMySql implements {
//
//    private DatabaseTestConfig databaseTestConfig;
//    private UserRepository userRepository;
//
//    public UserTestMySql() {
//        this.databaseTestConfig = new DatabaseTestConfig();
//        this.userRepository = new UserRepositoryMySQL(databaseTestConfig.getDataSource());
//    }
//
//    @BeforeEach
//    public beforeTest(){
//        databaseTestConfig.dropTables();
//        databaseTestConfig.createDefaultsTable();
//    }
//
//}
