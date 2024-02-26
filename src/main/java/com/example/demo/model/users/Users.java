package com.example.demo.model.users;

import lombok.Data;

@Data
public class Users {


    private Long id;

    private String username;

    private String email;

    // Password should not be exposed in the object and stored securely (not shown here)
    private String pwd;

    private String firstName;

    private String lastName;

    private String address;

    private String mobileNumber;


}
