package com.example.demo.services;

import com.example.demo.services.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
    Optional<UserDetails> findByUserID(int userID);
}
