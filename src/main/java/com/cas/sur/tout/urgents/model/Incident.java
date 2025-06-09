package com.cas.sur.tout.urgents.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Incident implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String decrireAction;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "user_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "user_longitude"))
    })
    private UserLocation userLocation;

    private String url;
    private boolean active;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Africa/Nouakchott", pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Africa/Nouakchott", pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime dateTraitement;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Africa/Nouakchott", pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime dernierChEta;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_zone", referencedColumnName = "id")
    @JsonBackReference
    private Zone zone;

    @ManyToOne
    @JoinColumn(name = "id_degree", referencedColumnName = "id")
    @JsonBackReference
    private Degree degree;

    @ManyToOne
    @JoinColumn(name = "id_cas", referencedColumnName = "id_cas")
    @JsonBackReference
    private TypeCas typeCas;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    @JsonBackReference
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_gestionnaire", referencedColumnName = "id")
    @JsonBackReference
    private Gestionnaire gestionnaire;

    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDateTime.now(); // Définit la date actuelle automatiquement
    }

    @PreUpdate
    protected void onChangeEtat() {
        this.dernierChEta = LocalDateTime.now();
    }

}