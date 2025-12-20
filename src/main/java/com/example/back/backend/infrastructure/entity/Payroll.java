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
@Table(name = "PAYROLL")
@ToString
public class Payroll {

    @Id
    private String refNo;
    private BigDecimal trxAmt;
    private BigDecimal foodAmount;
    private BigDecimal thr;
    private BigDecimal bonus;
    private BigDecimal tiketAmt;
    private Integer hisNo;
    private String noRekening;
    private String remark;
    private LocalDate lastSalaryIncreaseDt;
    private LocalDate tiketBuyDt;
    private LocalDate regDt;
    private LocalTime regTm;
    private LocalDate updDt;
    private LocalTime updTm;

}
