package com.example.sping_portfolio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.example.sping_portfolio.model.UserTaskE;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.io.*;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inMemoryUserDetailsManager());

//        auth.inMemoryAuthentication()
//            .withUser("user1").password(passwordEncoder().encode("testPassword1!")).roles("USER")
//            .and()
//            .withUser("admin").password(passwordEncoder().encode("adminPassword1!")).roles("ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/adminPage").hasRole("ADMIN")
            .antMatchers("/login").permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .failureUrl("/login?error=true")
            .permitAll()
            .failureHandler(authenticationFailureHandler())
            .and()
            .logout()
            .logoutSuccessUrl("/login?logout=true")
            .permitAll()
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID");
//            .logoutSuccessHandler(logoutSuccessHandler());
    }

    public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

        private ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                String username = request.getParameter("username");
                String error = exception.getMessage();
                String errorMsg = "ErrorMsg: " + error;
                System.out.println(errorMsg);

                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                
                Map<String, Object> data = new HashMap<>();
                data.put("timestamp", new Date());
                data.put("status",HttpStatus.UNAUTHORIZED.value());
                data.put("message", "Unauthorized");
                data.put("path", request.getRequestURL().toString());
                data.put("username", username);
                data.put("errorMsg", error);
                String json = objectMapper.writeValueAsString(data);
                System.out.println(json);
                OutputStream output = response.getOutputStream();
//                objectMapper.writeValue(output, data);
//                output.flush();

                String redirectUrl = request.getContextPath() + "/login?error=true";
                response.sendRedirect(redirectUrl);
        }

    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager()
    {
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(UserTaskE.withUsername("user1").password(passwordEncoder().encode("testPassword1!"))
                .roles("USER").build());
        userDetailsList.add(UserTaskE.withUsername("admin").password(passwordEncoder().encode("testPassword1!"))
                .roles("ADMIN", "USER").build());
        return new InMemoryUserDetailsManager(userDetailsList);
    }

}

