package com.cas.sur.tout.urgents.config;

import com.cas.sur.tout.urgents.service.auth.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.cas.sur.tout.urgents.utils.Constants.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] ADMIN_LIST_URL = {
            DEGREE_ENDPOINT + "/add",
            DEGREE_ENDPOINT + "/find/**",
            DEGREE_ENDPOINT + "/isActive/**",
            GESTIONNAIRE_ENDPOINT + "/all",
            GESTIONNAIRE_ENDPOINT + "/isActive/**",
            GESTIONNAIRE_ENDPOINT + "/update",
            ORGANISME_EXTERIEUR_ENDPOINT + "/add",
            ORGANISME_EXTERIEUR_ENDPOINT + "/find/**",
            ORGANISME_EXTERIEUR_ENDPOINT + "isActive/**",
            ROLE_ENDPOINT + "/**",
            TYPE_CAS_ENDPOINT + "/find/**",
            TYPE_CAS_ENDPOINT + "/isActive/**",
            USER_ENDPOINT + "/**"
    };
    private static final String[] USER_LIST_URL = {
            INCIDENT_ENDPOINT + "/**",
            GESTIONNAIRE_ENDPOINT + "/find/**",
            ORGANISME_EXTERIEUR_ENDPOINT + "/all",
            ORGANISME_EXTERIEUR_ENDPOINT + "/orgByName/**",
            CLIENT_ENDPOINT + "/all",
            CLIENT_ENDPOINT + "/find/**",
            CLIENT_ENDPOINT + "/isActive/**",
            DEGREE_ENDPOINT + "/update",
            ZONE_ENDPOINT + "/**",
            TYPE_CAS_ENDPOINT + "/all",
            TYPE_CAS_ENDPOINT + "/add",
    };
    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private EncoderConfig encoder;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource())
                )
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(unauthorizedHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ADMIN_LIST_URL).hasRole("ADMIN")
                        .requestMatchers(USER_LIST_URL).hasAnyRole("USER", "ADMIN", "RESPONSABLE")
                        .requestMatchers(
                                AUTH_ENDPOINT + "/**",
                                IMAGE_ENDPOINT + "/**",
                                CLIENT_ENDPOINT + "/add",
                                GESTIONNAIRE_ENDPOINT + "/add",
                                EXISTING_USER_ENDPOINT + "/exists",
                                OTP_ENDPOINT + "/**"
                                ).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(STATELESS)
                );

        // Add the JWT Token filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder.passwordEncoder());

        return authenticationManagerBuilder.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:4200", "http://192.168.100.27:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
