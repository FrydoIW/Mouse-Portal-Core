package com.example.back.backend.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class TKD0500AInput {

    //MEMBER
    String name;
    String address;
    LocalDate birthDt;
    String gender;
    String email;
    @NotNull
    String oldEmail;

    // MEMBER_INFO
    String position;
    private LocalDate joinWorkDt;
    private String religion;
    private String workingWeb;

    // PAYROLL
    private BigDecimal salaryAmount;
    private BigDecimal foodAmount;
    private BigDecimal thr;
    private BigDecimal bonus;
    private BigDecimal ticketAmt;
    private LocalDate ticketBuyDt;
    private String noRekening;
    private LocalDate lastSalaryIncreaseDt;
}
