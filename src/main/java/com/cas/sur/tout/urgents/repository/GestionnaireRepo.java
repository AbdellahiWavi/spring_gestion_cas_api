package com.cas.sur.tout.urgents.repository;

import com.cas.sur.tout.urgents.dto.GestionnaireDto;
import com.cas.sur.tout.urgents.model.Gestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GestionnaireRepo extends JpaRepository<Gestionnaire, Long> {
    Optional<Gestionnaire> findUserByEmail(String email);
}
