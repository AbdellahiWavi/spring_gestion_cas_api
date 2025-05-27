package com.cas.sur.tout.urgents.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@PrimaryKeyJoinColumn(name = "id")
public class Gestionnaire extends User {

    @Column(unique = true, nullable = false)
    private String email;
    private boolean active;

    @OneToMany(mappedBy = "gestionnaire")
    @JsonManagedReference
    private List<Incident> incidents;

}
