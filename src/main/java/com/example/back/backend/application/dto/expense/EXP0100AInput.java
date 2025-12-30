package com.example.back.backend.application.dto.expense;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EXP0100AInput {

    private String expenseName;
    private BigDecimal cost;
    private LocalDate dueDate;
    private String memo;
    private Long branchId;

}
