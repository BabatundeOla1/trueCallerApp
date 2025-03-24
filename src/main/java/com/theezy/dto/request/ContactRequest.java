package com.theezy.dto.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ContactRequest {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}
