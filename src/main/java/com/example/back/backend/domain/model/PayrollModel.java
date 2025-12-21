package com.example.back.backend.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PayrollModel {

    BigDecimal trxAmt = BigDecimal.ZERO;
    String refNo;
    String remark;
    String payrollRemark;
    private BigDecimal foodAmount;
    private BigDecimal thr;
    private BigDecimal bonus;
    private BigDecimal ticketAmt;
    private LocalDate ticketBuyDt;
    private String noRekening;
    private LocalDate lastSalaryIncreaseDt;


}
