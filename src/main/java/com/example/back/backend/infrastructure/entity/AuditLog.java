package com.example.back.backend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit_log")
@ToString
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String tableName;
    String pkValue;
    String action;
    LocalDateTime changedAt;
    int changedBy; // optional (adminId)
    String changedByName;
    String workspaceId;
    String changesJson;

}
