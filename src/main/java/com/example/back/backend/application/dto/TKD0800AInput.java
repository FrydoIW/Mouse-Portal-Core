package com.example.back.backend.application.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TKD0800AInput {

    private Long id;
    private String expenseName;
    private BigDecimal cost;
    private LocalDate dueDate;

}
