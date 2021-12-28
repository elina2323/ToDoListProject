package com.example.todolistproject.controller.v1;

import com.example.todolistproject.security.jwt.JwtTokenProvider;
import com.example.todolistproject.model.dto.AuthenticationRequestDto;
import com.example.todolistproject.model.dto.UserDto;
import com.example.todolistproject.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
@CrossOrigin
public class AuthenticationRestController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final AuthenticationManager authenticationManager;

    //private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, /*JwtTokenProvider jwtTokenProvider,*/ UserService userService) {
        this.authenticationManager = authenticationManager;
        //this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("sign_up")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {

        log.info("IN AuthenticationRestController saveUser - user {} successfully saved", userDto);

        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("login")
//    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {

        String username = requestDto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        UserDto userDto = userService.findByAuthorName(username);
        return ResponseEntity.ok(userDto);

    }
//        try {
//            String username = requestDto.getUsername();
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
//            UserDto userDto = userService.findByAuthorName(username);
//
//            if (userDto == null) {
//                throw new UsernameNotFoundException("User with username: " + username + " not found");
//            }
//
//            String token = jwtTokenProvider.createToken(username, userDto.getRoles());
//
//            Map<Object, Object> response = new HashMap<>();
//            response.put("username", username);
//            response.put("token", token);
//
//
//            log.info("IN AuthenticationRestController login - user {} successfully logged", requestDto);
//
//            return ResponseEntity.ok(response);
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Invalid username or password");
//        }
//    }
}
