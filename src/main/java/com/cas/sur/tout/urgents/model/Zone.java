package com.cas.sur.tout.urgents.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Geometry;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Zones")
public class Zone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "city")
    private String city;

    @Lob
    @Column(columnDefinition = "geometry", nullable = false)
    private Geometry geometry;

    @OneToMany(mappedBy = "zone")
    @JsonManagedReference
    private List<Incident> incidents;

}