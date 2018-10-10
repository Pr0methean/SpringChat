package com.chat.api.restUser;

import com.chat.application.dto.NewUserDTO;
import com.chat.application.dto.UserDTO;
import com.chat.application.sercices.NewUserMapper;
import com.chat.application.sercices.UserMapper;
import com.chat.domain.model.NewUser;
import com.chat.domain.model.ServerLog;
import com.chat.domain.model.User;
import com.chat.domain.ports.UserNotFindException;
import com.chat.domain.ports.UserReposytory;
import com.chat.infrastructure.DbLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Controller

public class RestUser {

    private UserReposytory userReposytory;
    private DbLogger dbLogger;
    private UserMapper userMapper;
    private NewUserMapper newUserMapper;

    @Autowired
    public RestUser(DbLogger dbLogger, UserReposytory userReposytory, UserMapper userMapper, NewUserMapper newUserMapper) {
        this.userReposytory = userReposytory;
        this.dbLogger = dbLogger;
        this.userMapper = userMapper;
        this.newUserMapper = newUserMapper;
    }


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

                UserDTO userDTO = new UserDTO();
                userDTO.setNick(user.getNick());
                userDTO.setId(user.getId());
               // userDTO.addLik(new Link("self", "/users/" + user.getId()));
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
    @PostMapping("/users") // -- dodaje usera do bazy danych (@RequestBody NewUserDTO newUser)
                                    // -- otrzymujemy od frontu, zadanie zasoby
    public UserDTO newUser(HttpServletRequest request, HttpServletResponse response, @RequestBody NewUser newUser) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type","application/json");

        try {

            int userID = userReposytory.saveUser(newUser);
            System.out.println(" Add new user to db " + newUser.toString());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(userID);
            userDTO.setNick(newUser.getNick());

            //userDTO.addLik(new Link("self", "/users/" + userID));

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

            User user = userReposytory.fetchUserBy(userID);
            UserDTO userDTO = this.userMapper.mapToUserDTO(user);
            //userDTO.addLik(new Link("self", "users/" + userDTO.getId()));
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
    public UserDTO repleaceUser(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int userID, @RequestBody NewUser newUser) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type","application/json");
        try {
            newUser.setId(userID);
            userReposytory.updateUser(newUser);
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
    public UserDTO updateUser(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int userID, @RequestBody NewUser newUser) throws UserNotFindException {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type","application/json");


        try {

            NewUser newUserDB = userReposytory.fetchNewUserwBy(userID);

            final String receivedNick = newUser.getNick();
            final String receivedPass = newUser.getPassword();
            if(receivedNick == null){
                newUser.setNick(newUserDB.getNick());
            }
            if(receivedPass == null){
                newUser.setPassword(newUserDB.getPassword());
            }

            System.out.println(newUser);
            newUser.setId(userID);
            userReposytory.updateUser(newUser);
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





