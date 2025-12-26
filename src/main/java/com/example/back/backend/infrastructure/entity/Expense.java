package com.example.back.backend.infrastructure.entity;

import com.example.back.backend.infrastructure.entity.PK.HistoryPk;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EXPENSE")
@ToString
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String expenseName;
    private BigDecimal cost;
    private LocalDate dueDate;
    private LocalDate updDt;
    private LocalDate regDt;

}
