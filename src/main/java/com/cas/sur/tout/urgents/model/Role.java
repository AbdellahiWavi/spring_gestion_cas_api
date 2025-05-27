package com.cas.sur.tout.urgents.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;
    private String profile;
    private boolean active;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    @Builder.Default List<User> users = new ArrayList<>();

}
