package com.cas.sur.tout.urgents.dto;

import com.cas.sur.tout.urgents.model.TypeCas;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"incidents"})
public class TypeCasDto {

    private Integer id_cas;

    private String type;
    private boolean active;

    private OrganismeExtDto destination;

    private List<IncidentDto> incidents;


    public static TypeCasDto fromEntity(TypeCas typeCas) {
        if (typeCas == null) {
            return null;
            // TODO throw an exception
        }

        return TypeCasDto.builder()
                .id_cas(typeCas.getId_cas())
                .type(typeCas.getType())
                .destination(OrganismeExtDto.fromEntity(typeCas.getDestination()))
                .active(typeCas.isActive())
                .build();
    }

    public static TypeCas toEntity(TypeCasDto typeCas) {
        if (typeCas == null) {
            return null;
            // TODO throw an exception
        }

        return TypeCas.builder()
                .id_cas(typeCas.getId_cas())
                .type(typeCas.getType())
                .destination(OrganismeExtDto.toEntity(typeCas.getDestination()))
                .active(typeCas.isActive())
                .build();
    }
}
