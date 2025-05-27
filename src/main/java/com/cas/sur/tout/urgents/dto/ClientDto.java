package com.cas.sur.tout.urgents.dto;

import com.cas.sur.tout.urgents.model.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"incidents"})
public class ClientDto extends UserDto {

    private String tel;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateCreation;

    private List<IncidentDto> incidents;

    public static ClientDto fromEntity(Client client) {
        if(client == null) {
            return null;
            // TODO throw an exception
        }

        return ClientDto.builder()
                .id(client.getId())
                .username(client.getUsername())
                .password(client.getPassword())
                .tel(client.getTel())
                .dateCreation(client.getDateCreation())
                .active(client.isActive())
                .roles(
                        client.getRoles() != null ?
                            client.getRoles().stream()
                                    .map(RoleDto::fromEntity)
                                    .collect(Collectors.toList()) : null
                )
                .build();
    }

    public static Client toEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
            // TODO throw an exception
        }

        return Client.builder()
                .id(clientDto.getId())
                .username(clientDto.getUsername())
                .password(clientDto.getPassword())
                .tel(clientDto.getTel())
                .dateCreation(clientDto.getDateCreation())
                .active(clientDto.isActive())
                .roles(
                        clientDto.getRoles() != null ?
                            clientDto.getRoles().stream()
                                    .map(RoleDto::toEntity)
                                    .collect(Collectors.toList()) : null
                )
                .build();
    }
}
