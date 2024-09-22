package dev.raj.paymentapi.Config;
//import dev.raj.paymentapi.Services.UserdetailServe;
//import dev.raj.paymentapi.Services.UserdetailServe;
//import dev.raj.paymentapi.Services.UserdetailServe;
import dev.raj.paymentapi.Services.UserdetailServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    UserdetailServe userdetailServe;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println();
            auth.userDetailsService(userdetailServe).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
           http.authorizeRequests()
                   .antMatchers("/signup").permitAll()
                   .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
                   .anyRequest().authenticated()
                           .and().httpBasic();

           http.csrf().disable();
    }




//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
//                                .requestMatchers("/signup").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .csrf(csrf -> csrf.disable()) // disable CSRF protection for /signup
//                .sessionManagement(sessionManagement ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .httpBasic(withDefaults());
//        return http.build();
//    }
   // @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 = User.withUsername("user")
//            .password("{noop}password")
//            .roles("USER")
//                .authorities("read")
//                .build();
//
//
//        UserDetails admin= User.withUsername("admin")
//                .password("{noop}password")
//                .authorities("ROLE_ADMIN", "read", "write")
//                .build();
//
//
//
//        return new InMemoryUserDetailsManager(user1,admin);
//    }
}