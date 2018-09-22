package com.supertask.chat.api;

import com.supertask.chat.api.restUser.UserDTO;
import com.supertask.chat.api.restUser.UserNew;
import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.model.dto.Link;
import com.supertask.chat.domain.ports.UserNotFindException;
import com.supertask.chat.domain.ports.UserReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Controller

public class RestUser {

    @Autowired //- pobieranie obiektu z kontenera i zapisaniego do zmiennej
    private UserReposytory userReposytory;

    @ResponseBody
    @GetMapping("/users")
    public List<UserDTO> getUsers(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> users = userReposytory.fetchAllUsers();
            response.setStatus(200);

            List<UserDTO> usersDTOS = new ArrayList<>();

            for (User user : users) {
                UserDTO userDTO = new UserDTO(user);
                userDTO.addLik(new Link("self", "/users/" + user.getId()));
                usersDTOS.add(userDTO);
            }
            return usersDTOS;


        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
        }
        return null;
    }

    @ResponseBody
    @PostMapping("/users")
    public UserDTO newUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserNew userNew) {

        try {

            int userID = userReposytory.saveUser(userNew);
            System.out.println(" Add new user to db " + userNew.toString());
            response.setStatus(201);


            UserDTO userDTO = new UserDTO(new User(userID, userNew.getNick()));
            userDTO.addLik(new Link("self", "/users/" + userID));
            return userDTO;

        } catch (Exception e) {
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
        }
        return null;
    }

    @ResponseBody
    @GetMapping("/users/{id}")
    public UserDTO getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int userID) {

        try {
            UserDTO userDTO = new UserDTO(userReposytory.fetchUserBy(userID));
            userDTO.addLik(new Link("self", "users/" + userDTO.getId()));

            return userDTO;

        } catch (UserNotFindException e) {
            e.printStackTrace();
            response.setStatus(404);
            response.setHeader("ErrorMessage", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
        }
        return null;

    }


    @ResponseBody
    @PutMapping("/users/{id}")
    public UserDTO repleaceUser(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int userID, @RequestBody UserNew userNew) {
        try {
            userNew.setId(userID);
            userReposytory.updateUser(userNew);
            response.setStatus(200);

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
        }
        return null;
    }

    @ResponseBody
    @DeleteMapping("/users/{id}")
    public void deleteUser(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int userID) {
        try {
            System.out.printf("id " + userID);
            userReposytory.deleteUserBy(userID);
            response.setStatus(200);

        } catch (Exception e) {
            response.setStatus(404);
            response.setHeader("ErrorMessage", e.getMessage());
        }
    }

    @ResponseBody
    @PatchMapping("/users/{id}")
    public UserDTO updateUser(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int userID, @RequestBody UserNew userNew) throws UserNotFindException {


        try {
            final UserNew userNewDB = userReposytory.fetchUserNewBy(userID);

            final String receivedNick = userNew.getNick();
            final String receivedPass = userNew.getPassword();

            if(receivedNick == null){
                userNew.setNick(userNewDB.getNick());
            }
            if(receivedPass == null){
                userNew.setPassword(userNewDB.getPassword());
            }

            try {

                System.out.println(userNew);
                userNew.setId(userID);
                userReposytory.updateUser(userNew);

            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(409);
                response.setHeader("ErrorMessage", e.getMessage());
            }

        }catch( UserNotFindException e){
            response.setStatus(404);
            response.setHeader("ErrorMessage", e.getMessage());
        }

        return null;
    }

}





