package com.cas.sur.tout.urgents.repository;

import com.cas.sur.tout.urgents.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepo extends JpaRepository<Incident, Long> {

    List<Incident> findAllByClientId(Long clientId);

}
