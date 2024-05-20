package com.fantasyfreakz.fplc.service;


import com.fantasyfreakz.fplc.domain.entites.UserCredential;
import com.fantasyfreakz.fplc.domain.entites.UserInfo;
import com.fantasyfreakz.fplc.domain.mapper.UserInfoMapper;
import com.fantasyfreakz.fplc.domain.model.request.UserCredentialDTO;
import com.fantasyfreakz.fplc.domain.model.request.UserInfoDTO;
import com.fantasyfreakz.fplc.domain.model.request.UserInfoWithCredentialDTO;
import com.fantasyfreakz.fplc.domain.model.response.UserInfoResponseDTO;
import com.fantasyfreakz.fplc.repository.UserCredentialRepository;
import com.fantasyfreakz.fplc.repository.UserInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserCredentialRepository userCredentialRepository;


    public UserInfoResponseDTO getUserByName(String username) {
        UserInfo userInfo = userInfoRepository.findByName(username);

        if(userInfo == null) {
            throw new RuntimeException();
        }
        return UserInfoMapper.mapUserInfoToUserInfoDTO(userInfo);
    }

    public List<UserInfo> getAllUser() {
        return userInfoRepository.findAll();
    }

    @Transactional
    public void createUserWithCredentialsAndInfo(UserInfoWithCredentialDTO requestDto) {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername(requestDto.getCredentialDTO().getUserName());
        userCredential.setPassword(requestDto.getCredentialDTO().getPassword());
        UserCredential savedUserCredential = userCredentialRepository.save(userCredential);

        UserInfo userInfo = new UserInfo();
        userInfo.setName(requestDto.getFullName());
        userInfo.setDesignation(requestDto.getDesignation());
        userInfo.setSalary(requestDto.getSalary());
        userInfo.setUserCredential(savedUserCredential);
        userInfoRepository.save(userInfo);
    }

    public UserInfo updateUserInfo(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = userInfoRepository.findByUserCredentialUsername(userInfoDTO.getUserName());

        if(userInfo == null) {
            throw new RuntimeException("No such user");
        }
        userInfo.setName(userInfoDTO.getFullName());
        userInfo.setDesignation(userInfoDTO.getDesignation());
        userInfo.setSalary(userInfoDTO.getSalary());
        userInfo = userInfoRepository.save(userInfo);

        return userInfo;
    }

    public UserCredential updatePassword(UserCredentialDTO userCredentialDTO) {

        UserCredential userInfo = userCredentialRepository.findByUsername(userCredentialDTO.getUserName());

        if(userInfo == null) {
            throw new RuntimeException("No such user");
        }
        userInfo.setPassword(userCredentialDTO.getPassword());
        return userCredentialRepository.save(userInfo);
    }

}
