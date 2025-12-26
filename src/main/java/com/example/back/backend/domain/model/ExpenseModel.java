package com.example.back.backend.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ExpenseModel {

    private String expenseName;
    private BigDecimal cost;
    private LocalDate dueDate;

}
