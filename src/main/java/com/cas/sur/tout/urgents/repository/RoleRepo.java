package com.cas.sur.tout.urgents.repository;

import com.cas.sur.tout.urgents.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(String role);

    @Query(value = "SELECT * FROM role WHERE role = :role AND profile = :profile", nativeQuery = true)
    Role findByRoleProfile(@Param("role") String role, @Param("profile") String profile);

}
