package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoBranchJpa extends JpaRepository<Branch,Long> {
}
