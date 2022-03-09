package com.getir.bookstore.security.web.model.request;

import com.getir.bookstore.security.web.model.enumeration.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignupRequest {
    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    private Role role;
}
