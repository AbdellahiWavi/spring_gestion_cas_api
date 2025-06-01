package com.cas.sur.tout.urgents.dto.auth;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
public class UserInfo {

    private Long id;
    private String username;
    private String emailOrTel;
    private Collection<? extends GrantedAuthority> role;
}
