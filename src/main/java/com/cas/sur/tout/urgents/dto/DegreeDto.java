package com.cas.sur.tout.urgents.dto;

import com.cas.sur.tout.urgents.model.Degree;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DegreeDto {

    public Long id;

    public String type_degree;
    private boolean active;

    public static DegreeDto fromEntity(Degree degree) {
        if (degree == null) {
            return null;
            // TODO throw an exception
        }

        return DegreeDto.builder()
                .id(degree.getId())
                .type_degree(degree.getType_degree())
                .active(degree.isActive())
                .build();
    }

    public static Degree toEntity(DegreeDto degree) {
        if (degree == null) {
            return null;
            // TODO throw an exception
        }

        return Degree.builder()
                .id(degree.getId())
                .type_degree(degree.getType_degree())
                .active(degree.isActive())
                .build();
    }
}
