package com.cas.sur.tout.urgents.service.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class ApplicationUserDetails implements UserDetails {

    @Getter
    private Long id;
    private final String username;
    @Getter
    private String emailOrtTel;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public ApplicationUserDetails(
            Long id, String username, String tel, String password,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.username = username;
        this.emailOrtTel = tel;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
