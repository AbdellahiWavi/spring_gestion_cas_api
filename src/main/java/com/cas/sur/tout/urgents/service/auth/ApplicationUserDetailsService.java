package com.cas.sur.tout.urgents.service.auth;

import com.cas.sur.tout.urgents.dto.ClientDto;
import com.cas.sur.tout.urgents.dto.GestionnaireDto;
import com.cas.sur.tout.urgents.service.ClientService;
import com.cas.sur.tout.urgents.service.impl.GestionnaireServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.stream.Collectors;

@Slf4j
@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private GestionnaireServiceImpl service;

    @Autowired
    private ClientService clientService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String appType = getCurrentRequestHeader();
        if (username.contains("@")) {
            // Gestionnaire
            if (!"WEB".equalsIgnoreCase(appType)) {
                throw  new AccessDeniedException("Les gestionnaires ne peuvent se connecter que via l'application web.");
            }
            GestionnaireDto user = service.findUserByEmail(username);
            return new ApplicationUserDetails(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.isActive(),
                    user.getPassword(),
                    user.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                            .collect(Collectors.toList())
            );
        } else {
            // Client
            if (!"MOBILE".equalsIgnoreCase(appType)) {
                throw new  AccessDeniedException("Les clients ne peuvent se connecter que via l'application mobile.");
            }
            ClientDto client = clientService.findClientByTel(username);
            return new ApplicationUserDetails(
                    client.getId(),
                    client.getUsername(),
                    client.getTel(),
                    client.isActive(),
                    client.getPassword(),
                    client.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                            .collect(Collectors.toList())
            );
        }
    }

    private String getCurrentRequestHeader() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr != null) {
            HttpServletRequest request = attr.getRequest();
            return request.getHeader("X-App-Type");
        }
        return null;
    }
}
