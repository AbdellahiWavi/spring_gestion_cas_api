package com.cas.sur.tout.urgents.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String type_degree;
    private boolean active;

    @OneToMany(mappedBy = "degree")
    @JsonManagedReference
    private List<Incident> incidents;


}
