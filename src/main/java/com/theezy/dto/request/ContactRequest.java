package com.theezy.dto.request;

import lombok.Data;

@Data
public class ContactRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String id;
    private String address;
}
