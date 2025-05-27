package com.cas.sur.tout.urgents.dto;

import com.cas.sur.tout.urgents.model.Zone;
import com.cas.sur.tout.urgents.utils.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.locationtech.jts.geom.Geometry;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZoneDto {

    private Integer id;

    private String city;

    @JsonSerialize(using = GeometrySerializer.class)
    private Geometry geometry;


    public static ZoneDto fromEntity(Zone place) {
        if(place == null) {
            return null;
            // TODO throw an exception
        }

        return ZoneDto.builder()
                .id(place.getId())
                .city(place.getCity())
                .geometry(place.getGeometry())
                .build();
    }

    public static Zone toEntity(ZoneDto place) {
        if(place == null) {
            return null;
            // TODO throw an exception
        }

        return Zone.builder()
                .id(place.getId())
                .city(place.getCity())
                .geometry(place.getGeometry())
                .build();
    }
}
