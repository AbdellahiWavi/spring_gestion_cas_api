package com.cas.sur.tout.urgents.dto;

import com.cas.sur.tout.urgents.model.Gestionnaire;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"incidents"})
public class GestionnaireDto extends UserDto {

    private String email;

    private List<IncidentDto> incidents;

    public static GestionnaireDto fromEntity(Gestionnaire gestionnaire) {
        if(gestionnaire == null) {
            return null;
            // TODO throw an exception
        }

        return GestionnaireDto.builder()
                .id(gestionnaire.getId())
                .username(gestionnaire.getUsername())
                .email(gestionnaire.getEmail())
                .active(gestionnaire.isActive())
                .password(gestionnaire.getPassword())
                .roles(
                        gestionnaire.getRoles() != null ?
                                gestionnaire.getRoles().stream()
                                        .map(RoleDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }

    public static Gestionnaire toEntity(GestionnaireDto gestionnaire) {
        if(gestionnaire == null) {
            return null;
            // TODO throw an exception
        }

        return Gestionnaire.builder()
                .id(gestionnaire.getId())
                .username(gestionnaire.getUsername())
                .email(gestionnaire.getEmail())
                .active(gestionnaire.isActive())
                .password(gestionnaire.getPassword())
                .roles(
                        gestionnaire.getRoles() != null ?
                                gestionnaire.getRoles().stream()
                                        .map(RoleDto::toEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }
}
