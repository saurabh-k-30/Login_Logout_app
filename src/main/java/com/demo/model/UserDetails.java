package com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "USER_DETAILS")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;

    private String email;

    private String address;

    private String qualification;

    private String password;

}
