package com.fantasyfreakz.fplc.controller;

import com.fantasyfreakz.fplc.domain.entites.UserInfo;
import com.fantasyfreakz.fplc.domain.model.UserDTO;
import com.fantasyfreakz.fplc.domain.model.UserLoginDTO;
import com.fantasyfreakz.fplc.service.JwtService;
import com.fantasyfreakz.fplc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/user")
    public String hello(@RequestParam String name) {
        UserDTO dto = userService.getUserByName(name);

        if(dto != null){
            return dto.toString();
        }

//        return ResponseEntity.ok().body(dto.getDesignation()).toString();
        return "Hello";
    }

    @GetMapping("/getAllUsers")
    public List<UserInfo> getAllUsers(){
        return userService.getAllUser();
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO user){

        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

            if(authentication.isAuthenticated()){
                return jwtService.generateToken(user.getUserName());
            }

        } catch (Exception e) {
            return "Invalid username or password";
        }

        return "User login failed";
    }
}
