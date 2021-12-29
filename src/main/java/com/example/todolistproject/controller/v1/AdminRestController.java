package com.example.todolistproject.controller.v1;

import com.example.todolistproject.model.dto.UserDto;
import com.example.todolistproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/")
@CrossOrigin
public class AdminRestController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id) {
        UserDto userDto = userService.findById(id);

        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        log.info("IN AdminRestController getUserById - user {} successfully found", id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("users/get")
    public ResponseEntity<List<?>> findAll(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }

    @GetMapping("users/get-by-name")
    public ResponseEntity<?> findByUserName(String username) {

        log.info("IN AdminRestController findByUserName - user {} successfully found", username);

        return new ResponseEntity<>(userService.findByAuthorName(username), HttpStatus.FOUND);
    }

    @GetMapping(value = "users/delete{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id){
        userService.delete(id);

        log.info("IN AdminRestController deleteById - user {} successfully deleted", id);

        return new ResponseEntity<>( HttpStatus.OK);
    }

}
