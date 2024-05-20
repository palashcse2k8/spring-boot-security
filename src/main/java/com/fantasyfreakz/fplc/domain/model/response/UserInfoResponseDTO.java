package com.fantasyfreakz.fplc.domain.model.response;

import lombok.Data;

@Data
public class UserInfoResponseDTO {

    private String userName;
    private String fullName;
    private String designation;
    private int salary;
}
