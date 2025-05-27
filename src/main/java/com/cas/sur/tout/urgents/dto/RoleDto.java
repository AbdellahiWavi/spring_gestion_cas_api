package com.cas.sur.tout.urgents.dto;

import com.cas.sur.tout.urgents.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"users"})
public class RoleDto {

    private Long id;
    private String role;
    private String profile;
    private boolean active;

    private List<UserDto> users;

    // mapping from Role to RoleDto
    public static RoleDto fromEntity(Role role) {
        if (role == null) {
            return null;
            // TODO throw an exception
        }

        return RoleDto.builder()
                .id(role.getId())
                .role(role.getRole())
                .profile(role.getProfile())
                .active(role.isActive())
                .build();
    }

    // mapping from RoleDto to Role
    public static Role toEntity(RoleDto role) {
        if (role == null) {
            return null;
            // TODO throw an exception
        }

        return Role.builder()
                .id(role.getId())
                .role(role.getRole())
                .profile(role.getProfile())
                .active(role.isActive())
                .build();
    }
}
