package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DaoAuditLog extends JpaRepository<AuditLog,Integer> {

    @Query(value = """
        SELECT *
        FROM audit_log
        WHERE 1=1
          AND (:workspaceId IS NULL OR :workspaceId = '' OR workspace_id = :workspaceId)
          AND (:adminId = 0 OR changed_by = :adminId)
          AND (:actionType IS NULL OR :actionType = '' OR action = :actionType)
          AND (:tableName IS NULL OR :tableName = '' OR table_name = :tableName)
          AND (:fromDt IS NULL OR changed_at >= :fromDt)
          AND (:toDtExclusive IS NULL OR changed_at < :toDtExclusive)
        ORDER BY changed_at DESC
        LIMIT :size OFFSET :offset
        """, nativeQuery = true)
    List<AuditLog> getHistoryData(
            @Param("workspaceId") String workspaceId,
            @Param("adminId") int adminId,
            @Param("actionType") String actionType,
            @Param("tableName") String tableName,
            @Param("fromDt") LocalDateTime fromDt,
            @Param("toDtExclusive") LocalDateTime toDtExclusive,
            @Param("size") int size,
            @Param("offset") int offset
    );

    @Query(value = """
        SELECT COUNT(*)
        FROM audit_log
        WHERE 1=1
          AND (:workspaceId IS NULL OR :workspaceId = '' OR workspace_id = :workspaceId)
          AND (:adminId = 0 OR changed_by = :adminId)
          AND (:actionType IS NULL OR :actionType = '' OR action = :actionType)
          AND (:tableName IS NULL OR :tableName = '' OR table_name = :tableName)
          AND (:fromDt IS NULL OR changed_at >= :fromDt)
          AND (:toDtExclusive IS NULL OR changed_at < :toDtExclusive)
        """, nativeQuery = true)
    long countHistoryData(
            @Param("workspaceId") String workspaceId,
            @Param("adminId") int adminId,
            @Param("actionType") String actionType,
            @Param("tableName") String tableName,
            @Param("fromDt") LocalDateTime fromDt,
            @Param("toDtExclusive") LocalDateTime toDtExclusive
    );

}
