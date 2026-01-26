package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoWorkspaceJpa extends JpaRepository<Workspace,String> {



}
