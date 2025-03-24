package com.theezy.data.models;

import lombok.Data;

@Data
public class User {
    private String name;
    private String password;
    private String phoneNumber;
    private String email;
}
