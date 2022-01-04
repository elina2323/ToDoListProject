package com.example.todolistproject.controller.v1;

import com.example.todolistproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
@CrossOrigin
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("get-by-name")
    public ResponseEntity<?> findByUserName(String username) {

        log.info("IN AdminRestController findByUserName - user {} successfully found", username);

        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.FOUND);
    }
}
