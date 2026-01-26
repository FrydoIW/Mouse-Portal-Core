package com.example.back.backend.infrastructure.jpa;


import com.example.back.backend.infrastructure.entity.PK.WorkspaceInfoPk;
import com.example.back.backend.infrastructure.entity.WorkspaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoWorkspaceInfoJpa extends JpaRepository<WorkspaceInfo, WorkspaceInfoPk> {



}
