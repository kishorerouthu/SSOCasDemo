package com.css.sso.cas;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Kishore Routhu on 25/6/17 9:13 AM.
 */
public class CasUserDetailsService implements AuthenticationUserDetailsService<Authentication> {


    public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
        String username = token.getName();
        Collection<? extends GrantedAuthority> authorities = token.getAuthorities();
        return new User(username, username, true, true, true, true, authorities);
    }
}
