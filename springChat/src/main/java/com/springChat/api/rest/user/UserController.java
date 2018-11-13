package com.springChat.api.rest.user;


import com.springChat.application.dto.user.UserRestDTO;
import com.springChat.application.services.UserMapper;
import com.springChat.domain.model.NewUser;
import com.springChat.domain.model.ServerLog;
import com.springChat.domain.model.User;
import com.springChat.domain.ports.UserReposytory;
import com.springChat.infrastructure.DbLogger;
import com.userRepository.applications.exceptions.RepositorySQLException;
import com.userRepository.applications.exceptions.UserNotExistException;
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
        System.out.println(httpHeaders.entrySet());
        return httpHeaders;
    }


    // TODO: 13.11.2018 HAVE TO REMOVE !!! 
    @GetMapping("/users/login/{nick},{pass}")
    public ResponseEntity<UserRestDTO> getUserByNickPass(HttpServletRequest request,
                                     @PathVariable("nick") String userNick, @PathVariable("pass") String userPass){

        HttpHeaders httpHeaders = this.getHeaders();
        try {
            UserRestDTO userDTO = this.userMapper.meptoUesrRestDTO(userReposytory.fetchUserBy(userNick,userPass));
            //userDTO.addLik(new Link("self", "users/" + userDTO.getId()));

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),201));
            return new ResponseEntity<>(userDTO, httpHeaders, HttpStatus.OK);
        } catch (Exception e) {

            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return new ResponseEntity<>( httpHeaders, HttpStatus.CONFLICT);
    }

    @CrossOrigin
    @PostMapping("/users/login")
    public ResponseEntity<UserRestDTO> getUserByNickAndPass(HttpServletRequest request, @RequestBody NewUser newUser){

        HttpHeaders httpHeaders = this.getHeaders();

        try {
            User user = userReposytory.fetchUserBy(newUser.getNick(), newUser.getPassword());

            UserRestDTO userRestDTO = userMapper.meptoUesrRestDTO(user);

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),201));
            return new ResponseEntity<>(userRestDTO, httpHeaders, HttpStatus.OK);

        } catch (UserNotExistException e) {
            e.printStackTrace();
        } catch (RepositorySQLException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("/users")
    public ResponseEntity<List<UserRestDTO>> getUsers(HttpServletRequest request) {

        HttpHeaders httpHeaders = getHeaders();

        try {

            List<User> users = userReposytory.fetchAllUsers();
            List<UserRestDTO> userRestDTOList = new ArrayList<>();

            for (User user : users) {
                UserRestDTO userRestDTO = new UserRestDTO();
                userRestDTO.setNick(user.getNick());
                userRestDTO.setIdUser(user.getId());

                Link link = linkTo(methodOn(UserController.class).getUser(null, user.getId())).withSelfRel();

                userRestDTO.add(link);

                userRestDTOList.add(userRestDTO);
            }

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 200));
            return new ResponseEntity<>(userRestDTOList, httpHeaders, HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CONFLICT);
    }

    @CrossOrigin
    @PostMapping("/users")
    public ResponseEntity<UserRestDTO> newUser(HttpServletRequest request, @RequestBody NewUser newUser) {

        HttpHeaders httpHeaders = getHeaders();
        try {

            int userID = userReposytory.saveUser(newUser);
            User user = new User();
            user.setId(userID);
            user.setNick(newUser.getNick());
            UserRestDTO userRestDTO = userMapper.meptoUesrRestDTO(user);

            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 201));
            return new ResponseEntity<>(userRestDTO, httpHeaders, HttpStatus.CREATED);

        } catch (Exception e) {

            e.printStackTrace();
            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 409));
        }
        return new ResponseEntity<>( httpHeaders, HttpStatus.CONFLICT);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserRestDTO> getUser(HttpServletRequest request, @PathVariable("id") int userID) {

        HttpHeaders httpHeaders = getHeaders();
        try {

            User user = userReposytory.fetchUserBy(userID);
            UserRestDTO userDTO = this.userMapper.meptoUesrRestDTO(user);

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
    public ResponseEntity<UserRestDTO> replaceUser(HttpServletRequest request, @PathVariable("id") int userID, @RequestBody NewUser newUser) {

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
    public ResponseEntity<UserRestDTO> updateUser(HttpServletRequest request, @PathVariable("id") int userID, @RequestBody NewUser newUserREST) {

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

            UserRestDTO newUserDTO = this.userMapper.meptoUesrRestDTO(new User(newUserREST.getId(),newUserREST.getNick()));
            return new ResponseEntity<>(newUserDTO, httpHeaders, HttpStatus.OK);

        } catch (Exception e) {

            httpHeaders.set("Error-message", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(), request.getRequestURL().toString(), 404));
        }

        return new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT);
    }

}





