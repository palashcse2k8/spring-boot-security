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

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private UserCredential userCredential;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private int salary;
}
