package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DaoBranchJpa extends JpaRepository<Branch,Long> {

    @Query(value = "SELECT workspace_id FROM branch WHERE BRANCH_ID = :branchId", nativeQuery = true)
    String getWorkspaceIdByBranchId(@Param("branchId") int branchId);

}
