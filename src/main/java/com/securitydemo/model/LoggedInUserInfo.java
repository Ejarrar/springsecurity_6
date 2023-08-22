package com.securitydemo.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class LoggedInUserInfo {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Set<String> roles;
}
