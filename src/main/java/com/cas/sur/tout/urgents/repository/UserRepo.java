package com.cas.sur.tout.urgents.repository;

import com.cas.sur.tout.urgents.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Long> {
}
