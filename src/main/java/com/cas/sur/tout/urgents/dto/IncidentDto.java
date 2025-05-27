package com.cas.sur.tout.urgents.dto;

import com.cas.sur.tout.urgents.model.Incident;
import com.cas.sur.tout.urgents.model.Status;
import com.cas.sur.tout.urgents.model.UserLocation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IncidentDto {

    private Long id;
    private String decrireAction;
    private UserLocation userLocation;
    private String url;
    private boolean active;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Africa/Nouakchott", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Africa/Nouakchott", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTraitement;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Africa/Nouakchott", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dernierChEta;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    private ZoneDto zone;

    @Getter
    private DegreeDto degree;

    @Getter
    private TypeCasDto typeCas;

    @Getter
    private ClientDto client;

    @Getter
    private GestionnaireDto gestionnaire;

    public static IncidentDto fromEntity(Incident incident) {
        if (incident == null) {
            return null;
            // TODO throw an exception
        }

        return IncidentDto.builder()
                .id(incident.getId())
                .url(incident.getUrl())
                .active(incident.isActive())
                .decrireAction(incident.getDecrireAction())
                .userLocation(incident.getUserLocation())
                .dateCreation(incident.getDateCreation())
                .dateTraitement(incident.getDateTraitement())
                .dernierChEta(incident.getDernierChEta())
                .status(incident.getStatus())
                .zone(ZoneDto.fromEntity(incident.getZone()))
                .degree(DegreeDto.fromEntity(incident.getDegree()))
                .typeCas(TypeCasDto.fromEntity(incident.getTypeCas()))
                .client(ClientDto.fromEntity(incident.getClient()))
                .gestionnaire(GestionnaireDto.fromEntity(incident.getGestionnaire()))
                .build();
    }

    public static Incident toEntity(IncidentDto incident) {
        if (incident == null) {
            return null;
            // TODO throw an exception
        }

        return Incident.builder()
                .id(incident.getId())
                .url(incident.getUrl())
                .active(incident.isActive())
                .decrireAction(incident.getDecrireAction())
                .userLocation(incident.getUserLocation())
                .dateCreation(incident.getDateCreation())
                .dateTraitement(incident.getDateTraitement())
                .dernierChEta(incident.getDernierChEta())
                .status(incident.getStatus())
                .zone(ZoneDto.toEntity(incident.getZone()))
                .degree(DegreeDto.toEntity(incident.getDegree()))
                .typeCas(TypeCasDto.toEntity(incident.getTypeCas()))
                .client(ClientDto.toEntity(incident.getClient()))
                .gestionnaire(GestionnaireDto.toEntity(incident.getGestionnaire()))
                .build();
    }

}
