package com.chat.api.rest.user;

import com.chat.application.dto.user.UserDTO;
import com.chat.application.dto.user.UserRestDTO;
import com.chat.application.services.UserMapper;
import com.chat.domain.model.NewUser;
import com.chat.domain.model.ServerLog;
import com.chat.domain.model.User;
import com.chat.domain.ports.UserNotFindException;
import com.chat.domain.ports.UserReposytory;
import com.chat.infrastructure.DbLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class UserController {

    private UserReposytory userReposytory;
    private DbLogger dbLogger;
    private UserMapper userMapper;

    @Autowired
    public UserController(DbLogger dbLogger, UserReposytory userReposytory, UserMapper userMapper) {
        this.userReposytory = userReposytory;
        this.dbLogger = dbLogger;
        this.userMapper = userMapper;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "*");
        httpHeaders.add("Content-type", "application/json");
        return httpHeaders;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserRestDTO>> getUsers(HttpServletRequest request) {

        HttpHeaders httpHeaders = getHeaders();

        try {

            List<User> users = userReposytory.fetchAllUsers();
            List<UserRestDTO> usersDTOS = new ArrayList<>();

            for (User user : users) {
                UserRestDTO userRestDTO = new UserRestDTO();
                userRestDTO.setNick(user.getNick());
                userRestDTO.setIdUser(user.getId());

                Link link = linkTo(methodOn(UserController.class).getUser(null, user.getId())).withSelfRel();

                userRestDTO.add(link);

                usersDTOS.add(userRestDTO);
            }

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return new ResponseEntity<>(usersDTOS, httpHeaders, HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CONFLICT);
    }


    @CrossOrigin
    @PostMapping("/users")
    public ResponseEntity<UserDTO> newUser(HttpServletRequest request, @RequestBody NewUser newUser) {

        HttpHeaders httpHeaders = getHeaders();
        try {

            int userID = userReposytory.saveUser(newUser);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userID);
            userDTO.setNick(newUser.getNick());

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return new ResponseEntity<>(userDTO, httpHeaders, HttpStatus.CREATED);

        } catch (Exception e) {

            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CONFLICT);
    }


    @CrossOrigin
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(HttpServletRequest request, @PathVariable("id") int userID) {

        HttpHeaders httpHeaders = getHeaders();
        try {

            User user = userReposytory.fetchUserBy(userID);
            UserDTO userDTO = this.userMapper.mapToUserDTO(user);

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return new ResponseEntity<>(userDTO, httpHeaders, HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT);

    }


    @CrossOrigin
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> replaceUser(HttpServletRequest request, @PathVariable("id") int userID, @RequestBody NewUser newUser) {

        HttpHeaders httpHeaders = getHeaders();
        try {
            newUser.setId(userID);
            userReposytory.updateUser(newUser);

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 200));
            return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }

        return new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT);
    }

    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(HttpServletRequest request, @PathVariable("id") int userID) {

        HttpHeaders httpHeaders = getHeaders();
        try {

            userReposytory.deleteUserBy(userID);

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 200));
            return new ResponseEntity(httpHeaders, HttpStatus.OK);

        } catch (Exception e) {
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 404));
        }
        return new ResponseEntity(httpHeaders, HttpStatus.CONFLICT);
    }


    @CrossOrigin
    @PatchMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(HttpServletRequest request, @PathVariable("id") int userID, @RequestBody NewUser newUserREST) {

        HttpHeaders httpHeaders = getHeaders();
        try {

            NewUser newUserDB = userReposytory.fetchNewUserBy(userID);


            if (newUserREST.getNick() == null) {
                newUserREST.setNick(newUserDB.getNick());
            }
            if (newUserREST.getPassword() == null) {
                newUserREST.setPassword(newUserDB.getPassword());
            }
            newUserREST.setId(userID);
            userReposytory.updateUser(newUserREST);

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));

            UserDTO newUserDTO = this.userMapper.mapToUserDTO(new User(newUserREST.getId(),newUserREST.getNick()));
            return new ResponseEntity<>(newUserDTO, httpHeaders, HttpStatus.OK);

        } catch (Exception e) {

            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 404));
        }

        return new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT);
    }

}





