package com.example.demo.model.auth;

import lombok.Data;

@Data
public class UserAuthen {


    private Long id;

    private String username;

    private String email;

    // Password should not be exposed in the object and stored securely (not shown here)
    private String pwd;

    private String firstName;

    private String lastName;

    private String mobileNumber;


}
