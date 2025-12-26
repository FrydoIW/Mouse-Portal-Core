package com.example.back.backend.infrastructure.jpa;

import com.example.back.backend.infrastructure.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoExpenseJpa extends JpaRepository<Expense,Long> {



}
