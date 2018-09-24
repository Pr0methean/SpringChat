package com.supertask.chat.api;

import com.supertask.chat.api.restUser.UserDTO;
import com.supertask.chat.api.restUser.UserNew;
import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.model.dto.Link;
import com.supertask.chat.domain.ports.LogReposytory;
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

//    @Autowired
//    private LogReposytory logReposytory;

    @ResponseBody // - zwroc zawartosc metody jesli tego nie ma to zaciagnie z plików z dysku
    @GetMapping("/users") // - tylko pobiera z bazy i przekazuje dalej - w tym wypadku liste
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

        } catch (UserNotFindException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
        }
        return null;
    }

    @ResponseBody
    @PostMapping("/users") // -- dodaje usera do bazy danych (@RequestBody UserNew userNew)
                                    // -- otrzymujemy od frontu, zadanie zasoby
    public UserDTO newUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserNew userNew) {

        try {

            int userID = userReposytory.saveUser(userNew);
            System.out.println(" Add new user to db " + userNew.toString());
            response.setStatus(201);


            UserDTO userDTO = new UserDTO(new User(userID, userNew.getNick()));
            userDTO.addLik(new Link("self", "/users/" + userID));
            return userDTO;

        } catch (UserNotFindException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
        }
        return null;
    }

    @ResponseBody
    @GetMapping("/users/{id}") // - get konkretnego usera  (@PathVariable("id")) -- wysylanie danych przez sciezke
                    // -- przekaze id ktore otrzymal z URL do zmiennej -- nie znasz ID
    public UserDTO getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int userID) {

        try {
            UserDTO userDTO = new UserDTO(userReposytory.fetchUserBy(userID));
            userDTO.addLik(new Link("self", "users/" + userDTO.getId()));
            System.out.println(request.getServletPath());
            return userDTO;

        } catch (UserNotFindException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
        }
        return null;

    }


    @ResponseBody
    @PutMapping("/users/{id}") // zamiana zasobu - jesli nie wstawisz jakiejs wartosci wstawi null do bazy danych --- znasz ID
    public UserDTO repleaceUser(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int userID, @RequestBody UserNew userNew) {
        userNew.setId(userID);
        userReposytory.updateUser(userNew);
        response.setStatus(200);

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

            System.out.println(userNew);
            userNew.setId(userID);
            userReposytory.updateUser(userNew);

        }catch( UserNotFindException e){
            response.setStatus(404);
            response.setHeader("ErrorMessage", e.getMessage());
        }

        return null;
    }

}





