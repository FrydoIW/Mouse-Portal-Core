package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DaoRekeningJpa extends JpaRepository<Rekening,Long> {



}
