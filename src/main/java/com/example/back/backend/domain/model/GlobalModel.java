package com.example.back.backend.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GlobalModel {

    // MEMBER TABLE
    private String refNo;
    private String name;
    private String address;
    private LocalDate birthDate;
    private String gender;
    private String email;

    //HISTORY TABLE
    private String hisType;
    private int hisNo;

    //MEMBER_INFO TABLE
    private String position;
    private String status;
    private LocalDate joinWorkDt;
    private String religion;
    private String workingWeb;

    //MEMBER_CREDENTIAL TABLE
    private String passwordHash;
    private String twoFactorSecret;

    //PAYROLL TABLE
    private BigDecimal trxAmt;
    private String remark;
    private BigDecimal foodAmount;
    private BigDecimal thr;
    private BigDecimal bonus;
    private BigDecimal ticketAmt;
    private LocalDate ticketBuyDt;
    private String noRekening;
    private LocalDate lastSalaryIncreaseDt;

    //REKENING
    private String nomorRekening;
    private String bank;
    private String owner;
    private BigDecimal amount;

}
