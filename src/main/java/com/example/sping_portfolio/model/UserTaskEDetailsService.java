//package com.example.sping_portfolio.model;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.json.JSONException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.stereotype.Component;
//
//import com.example.sping_portfolio.model.UserTaskE;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service("userDetailsService")
//public class UserTaskEDetailsService extends InMemoryUserDetailsManager {
//
//    @Override
//    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
//
//
//        UserDetails user = super.loadUserByUsername(username);
//        return buildUserForAuthentication((User) user, buildUserAuthority((Set<UserRole>) user.getAuthorities()));
//
//    }
//
//
//    private User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
//        String username = user.getUsername();
//        String password = user.getPassword();
//
//        return new UserTaskE(username, password, true, true, true, true, authorities);
//    }
//
//    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
//
//        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//
//        for (UserRole userRole : userRoles) {
//            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
//        }
//
//        return new ArrayList<GrantedAuthority>(setAuths);
//    }
//
//}