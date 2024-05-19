package com.fantasyfreakz.fplc.domain.entites;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_info_table")
public class UserInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String designation;
    private int salary;
}
