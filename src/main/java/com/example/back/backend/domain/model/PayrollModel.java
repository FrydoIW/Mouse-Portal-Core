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

    String refNo;
    BigDecimal salaryAmt = BigDecimal.ZERO;
    String remark;
    private BigDecimal foodAmount;
    private BigDecimal thr;
    private BigDecimal bonus;
    private String noRekening;
    private LocalDate lastSalaryIncreaseDt;


}
