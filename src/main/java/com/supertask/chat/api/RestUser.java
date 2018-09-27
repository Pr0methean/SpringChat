package com.supertask.chat.api;

import com.supertask.chat.api.restUser.UserDTO;
import com.supertask.chat.api.restUser.UserNew;
import com.supertask.chat.domain.model.ServerLog;
import com.supertask.chat.domain.model.User;
import com.supertask.chat.domain.model.dto.Link;
import com.supertask.chat.domain.ports.LogReposytory;
import com.supertask.chat.domain.ports.UserNotFindException;
import com.supertask.chat.domain.ports.UserReposytory;
import com.supertask.chat.domain.services.DbLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Controller

public class RestUser {

    @Autowired //- pobieranie obiektu z kontenera i zapisaniego do zmiennej
    private UserReposytory userReposytory;

    @Autowired
    private DbLogger dbLogger;

//    @Autowired
//    private LogReposytory logReposytory;

    @ResponseBody // - zwroc zawartosc metody jesli tego nie ma to zaciagnie z plików z dysku
    @GetMapping("/users") // - tylko pobiera z bazy i przekazuje dalej - w tym wypadku liste
    public List<UserDTO> getUsers(HttpServletRequest request, HttpServletResponse response) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type","application/json");
        try {
            List<User> users = userReposytory.fetchAllUsers();

            List<UserDTO> usersDTOS = new ArrayList<>();

            for (User user : users) {
                UserDTO userDTO = new UserDTO(user);
                userDTO.addLik(new Link("self", "/users/" + user.getId()));
                usersDTOS.add(userDTO);
            }

            response.setStatus(200);
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),201));
            return usersDTOS;

        } catch (UserNotFindException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),409));
        }
        return null;
    }

    @ResponseBody
    @CrossOrigin
    @PostMapping("/users") // -- dodaje usera do bazy danych (@RequestBody UserNew userNew)
                                    // -- otrzymujemy od frontu, zadanie zasoby
    public UserDTO newUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserNew userNew) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type","application/json");

        try {

            int userID = userReposytory.saveUser(userNew);
            System.out.println(" Add new user to db " + userNew.toString());

            UserDTO userDTO = new UserDTO(new User(userID, userNew.getNick()));
            userDTO.addLik(new Link("self", "/users/" + userID));

            response.setStatus(201);
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),201));
            return userDTO;

        } catch (UserNotFindException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),409));
        }
        return null;
    }

    @ResponseBody
    @CrossOrigin
    @GetMapping("/users/{id}") // - get konkretnego usera  (@PathVariable("id")) -- wysylanie danych przez sciezke
                    // -- przekaze id ktore otrzymal z URL do zmiennej -- nie znasz ID
    public UserDTO getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int userID) {

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type","application/json");
        try {
            UserDTO userDTO = new UserDTO(userReposytory.fetchUserBy(userID));
            userDTO.addLik(new Link("self", "users/" + userDTO.getId()));
            response.setStatus(201);
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),201));
            return userDTO;

        } catch (UserNotFindException e) {
            e.printStackTrace();
            response.setStatus(409);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),409));
        }
        return null;

    }


    @ResponseBody
    @CrossOrigin
    @PutMapping("/users/{id}") // zamiana zasobu - jesli nie wstawisz jakiejs wartosci wstawi null do bazy danych --- znasz ID
    public UserDTO repleaceUser(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int userID, @RequestBody UserNew userNew) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type","application/json");
        try {
            userNew.setId(userID);
            userReposytory.updateUser(userNew);
            response.setStatus(200);
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),200));
        } catch (Exception e) {
            e.printStackTrace();
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),409));
        }

        return null;
    }

    @ResponseBody
    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public void deleteUser(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int userID) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type","application/json");
        try {
            System.out.printf("id " + userID);
            userReposytory.deleteUserBy(userID);
            response.setStatus(200);
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),200));
        } catch (Exception e) {
            response.setStatus(404);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),404));
        }
    }

    @ResponseBody
    @CrossOrigin
    @PatchMapping("/users/{id}")
    public UserDTO updateUser(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int userID, @RequestBody UserNew userNew) throws UserNotFindException {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type","application/json");


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
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),201));

            response.setStatus(201);
        }catch( UserNotFindException e){
            response.setStatus(404);
            response.setHeader("ErrorMessage", e.getMessage());
            dbLogger.log(new ServerLog(Instant.now(), request.getMethod(),request.getRequestURL().toString(),404));
        }

        return null;
    }

}





