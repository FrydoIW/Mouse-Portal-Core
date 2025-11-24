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

    //MEMBER_CREDENTIAL TABLE
    private String passwordHash;

    //PAYROLL TABLE
    private BigDecimal trxAmt;
    private String payrollRemark;
    private String remark;
}
