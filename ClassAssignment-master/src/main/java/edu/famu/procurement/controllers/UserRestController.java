package edu.famu.procurement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.famu.procurement.models.RestProduct;
import edu.famu.procurement.models.User;

import edu.famu.procurement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserRestController {
    public UserService userService;

    @Autowired
    public UserRestController(UserService userService) {this.userService = userService;}

    @GetMapping("/user")
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserByID(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return userService.getUserByID(id);
    }

    @PostMapping("/user")
    public String postNewUser(@RequestBody String json ) throws JsonProcessingException, ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        User tempUser = new User(rootNode.get("employeeId"),rootNode.get("firstName"),rootNode.get("lastName"),
                rootNode.get("phoneNumber"), rootNode.get("approver"),rootNode.get("active"));


       return userService.postNewUser(tempUser);
    }


}
