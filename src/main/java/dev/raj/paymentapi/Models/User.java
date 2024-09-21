package dev.raj.paymentapi.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Getter
@Setter
@Document
public class User  {
    private String id;
    private String name;
    private String password;
    private Set<String> roles;
    private Set<String> authorities;

}
