package dev.raj.paymentapi.Services;

import dev.raj.paymentapi.DTOs.UserDto;
import dev.raj.paymentapi.Models.User;
import dev.raj.paymentapi.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public String SignUp(UserDto user){
        User user1 = new User();
        user1.setName(user.getName());
        user1.setPassword("{noop}"+ user.getPassword());
        user1.setRoles(user.getRoles());
        user1.setAuthorities(user.getAuthorities());
        userRepository.save(user1);
        return "user";
    }

    public void Login(){
        System.out.println("Login");
    }
}
