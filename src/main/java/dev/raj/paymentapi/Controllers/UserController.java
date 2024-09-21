package dev.raj.paymentapi.Controllers;

import dev.raj.paymentapi.DTOs.UserDto;
import dev.raj.paymentapi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public String SignUp(@RequestBody  UserDto user){
        userService.SignUp(user);
        return "user";
    }
    @GetMapping("/login")
    public void Login(){
        System.out.println("Login");
    }
}
