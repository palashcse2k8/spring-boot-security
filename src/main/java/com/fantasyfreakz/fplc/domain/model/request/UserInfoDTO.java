package com.fantasyfreakz.fplc.domain.model.request;

import lombok.Data;

@Data
public class UserInfoDTO {
    private String userName;
    private String fullName;
    private String designation;
    private int salary;
}
