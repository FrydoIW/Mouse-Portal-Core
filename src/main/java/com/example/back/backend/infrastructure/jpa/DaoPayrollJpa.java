package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoPayrollJpa extends JpaRepository<Payroll,String> {



}
