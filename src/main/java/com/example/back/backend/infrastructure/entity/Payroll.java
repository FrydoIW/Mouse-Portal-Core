package com.example.back.backend.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payroll")
@ToString
public class Payroll {

    @Id
    private String refNo;
    private BigDecimal salaryAmt;
    private String remark;
    private LocalDate regDt;
    private LocalTime regTm;
    private LocalDate updDt;
    private LocalTime updTm;
    private BigDecimal foodAmount;
    private BigDecimal thr;
    private BigDecimal bonus;
    private String noRekening;
    private LocalDate lastSalaryIncreaseDt;

}
