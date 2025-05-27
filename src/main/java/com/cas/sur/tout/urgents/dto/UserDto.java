package com.cas.sur.tout.urgents.dto;

import com.cas.sur.tout.urgents.model.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"roles"})
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private boolean active;

    private List<RoleDto> roles;

    public static UserDto fromEntity(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(
                        user.getRoles() != null ?
                                user.getRoles().stream()
                                        .map(RoleDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }

    public static User toEntity(UserDto user) {
        if (user == null) {
            return null;
        }

        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(
                        user.getRoles() != null ?
                                user.getRoles().stream()
                                        .map(RoleDto::toEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }
}
