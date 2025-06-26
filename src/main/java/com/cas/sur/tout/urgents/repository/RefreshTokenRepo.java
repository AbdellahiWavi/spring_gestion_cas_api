package com.cas.sur.tout.urgents.repository;

import com.cas.sur.tout.urgents.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByClientId(Long clientId);

    @Modifying
    @Query(value = "delete from refresh_token where client_id = :clientId", nativeQuery = true)
    void deleteByClientId(Long clientId);
}
