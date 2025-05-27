package com.cas.sur.tout.urgents.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TypeCas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cas;

    private String type;
    private boolean active;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_destination")
    private OrganismeExt destination;

    @OneToMany(mappedBy = "typeCas")
    @JsonIgnore
    private List<Incident> incidents;

}
