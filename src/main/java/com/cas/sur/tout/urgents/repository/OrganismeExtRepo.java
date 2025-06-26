package com.cas.sur.tout.urgents.repository;

import com.cas.sur.tout.urgents.model.OrganismeExt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganismeExtRepo extends JpaRepository<OrganismeExt, Integer> {
    Optional<OrganismeExt> findByName(String name);
}
