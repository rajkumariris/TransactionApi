package dev.raj.paymentapi.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String id;
    private String name;
    private String password;
    private Set<String> roles;
    private Set<String> authorities;

}
