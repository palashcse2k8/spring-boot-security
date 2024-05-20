package com.fantasyfreakz.fplc.domain.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserInfoWithCredentialDTO {
    private UserCredentialDTO credentialDTO;
    private String fullName;
    private String designation;
    private int salary;
}
