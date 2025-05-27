package com.cas.sur.tout.urgents.repository;

import com.cas.sur.tout.urgents.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Long> {

    Optional<Client> findClientByTel(String tel);

}
