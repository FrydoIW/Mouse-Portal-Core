package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoAuditLog extends JpaRepository<AuditLog,Integer> {
}
