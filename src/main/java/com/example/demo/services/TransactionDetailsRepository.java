package com.example.demo.services;

import com.example.demo.services.models.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, String> {
}
