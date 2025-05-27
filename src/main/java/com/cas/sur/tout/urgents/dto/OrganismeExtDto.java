package com.cas.sur.tout.urgents.dto;


import com.cas.sur.tout.urgents.model.OrganismeExt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"typeCas"})
public class OrganismeExtDto {

    private Integer idDestination;
    private String name;
    private String image;
    private boolean active;

    private List<TypeCasDto> typeCas;

    public static OrganismeExtDto fromEntity(OrganismeExt organismeExt) {
        if (organismeExt == null) {
            return null;
            // TODO throw an exception
        }

        return OrganismeExtDto.builder()
                .idDestination(organismeExt.getIdDestination())
                .name(organismeExt.getName())
                .image(organismeExt.getImage())
                .active(organismeExt.isActive())
                .build();
    }

    public static OrganismeExt toEntity(OrganismeExtDto destination) {
        if (destination == null) {
            return null;
            // TODO throw an exception
        }

        return OrganismeExt.builder()
                .idDestination(destination.getIdDestination())
                .name(destination.getName())
                .image(destination.getImage())
                .active(destination.isActive())
                .build();
    }
}
