package com.fantasyfreakz.fplc.domain.mapper;

import com.fantasyfreakz.fplc.domain.entites.UserInfo;
import com.fantasyfreakz.fplc.domain.model.response.UserInfoResponseDTO;

public class UserInfoMapper {

    public static UserInfoResponseDTO mapUserInfoToUserInfoDTO(UserInfo userInfo) {
        UserInfoResponseDTO result = new UserInfoResponseDTO();
        result.setUserName(userInfo.getName());
        result.setSalary(userInfo.getSalary());
        result.setDesignation(userInfo.getDesignation());
        result.setFullName(userInfo.getName());
        return result;
    }

    public static UserInfo mapUserInfoDTOToUserInfo(UserInfoResponseDTO userInfoResponseDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(userInfoResponseDTO.getFullName());
//        userInfo.setUserCredential(userInfoDTO.getUserName());
        userInfo.setSalary(userInfoResponseDTO.getSalary());
        userInfo.setDesignation(userInfoResponseDTO.getDesignation());

        return userInfo;
    }
}
