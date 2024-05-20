package com.fantasyfreakz.fplc.controller;

import com.fantasyfreakz.fplc.domain.entites.UserCredential;
import com.fantasyfreakz.fplc.domain.entites.UserInfo;
import com.fantasyfreakz.fplc.domain.model.request.UserInfoDTO;
import com.fantasyfreakz.fplc.domain.model.request.UserInfoWithCredentialDTO;
import com.fantasyfreakz.fplc.domain.model.response.UserInfoResponseDTO;
import com.fantasyfreakz.fplc.domain.model.request.UserCredentialDTO;
import com.fantasyfreakz.fplc.service.JwtService;
import com.fantasyfreakz.fplc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    //Get single user information
    @GetMapping("/getUser/{userName}")
    public UserInfoResponseDTO getUser(@PathVariable String userName) {
        UserInfoResponseDTO dto = userService.getUserByName(userName);

        if(dto != null){
            return dto;
        }
        return null;
    }

    @GetMapping("/getAllUsers")
    public List<UserInfo> getAllUsers(){
        return userService.getAllUser();
    }

    @PostMapping("/createUser")
    public ResponseEntity<Void> createUser(@RequestBody UserInfoWithCredentialDTO userInfoWithCredentialDTO){
        userService.createUserWithCredentialsAndInfo(userInfoWithCredentialDTO);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateUserInfo")
    public ResponseEntity<UserInfo> updateUserInfo(@RequestBody UserInfoDTO userInfoDTO){
        return ResponseEntity.ok(userService.updateUserInfo(userInfoDTO));
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<UserCredential> updatePassword(@RequestBody UserCredentialDTO userCredentialDTO){
        UserCredential userCredential = userService.updatePassword(userCredentialDTO);

        return ResponseEntity.ok(userCredential);
    }



    @PostMapping("/login")
    public String login(@RequestBody UserCredentialDTO user){

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
