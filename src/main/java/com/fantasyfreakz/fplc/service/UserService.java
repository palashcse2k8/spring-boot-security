package com.fantasyfreakz.fplc.service;


import com.fantasyfreakz.fplc.domain.entites.UserInfo;
import com.fantasyfreakz.fplc.domain.model.UserDTO;
import com.fantasyfreakz.fplc.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserDTO getUserByName(String username) {
        UserInfo userInfo = userInfoRepository.findByName(username);

        if(userInfo == null) {
            throw new RuntimeException();
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setName(userInfo.getName());
        userDTO.setDesignation(userInfo.getDesignation());
        return userDTO;
    }

    public List<UserInfo> getAllUser(){
        return userInfoRepository.findAll();
    }

}
