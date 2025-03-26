package com.theezy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginRequest {

    @NotEmpty(message = "email can not be empty")
    @NotNull(message = "email is required")
    @NotBlank(message = "email is require")
    private String email;

    @NotEmpty(message = "password can not be empty")
    @NotNull(message = "password is required")
    @NotBlank(message = "password is require")
    private String password;
}
