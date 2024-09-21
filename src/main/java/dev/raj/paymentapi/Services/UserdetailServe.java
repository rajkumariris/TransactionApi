package dev.raj.paymentapi.Services;

import dev.raj.paymentapi.Models.User;
import dev.raj.paymentapi.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserdetailServe implements UserDetailsService {
    @Autowired
    UserRepository UserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = UserRepository.findByName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(
                user.getRoles().stream()
                        .map(role ->
                                new SimpleGrantedAuthority(role))
                        .collect(Collectors.toList()));

        authorities.addAll(user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList()));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                authorities


        );


    }
}
