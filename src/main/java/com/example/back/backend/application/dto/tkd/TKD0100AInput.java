package com.example.back.backend.application.dto.tkd;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TKD0100AInput {

    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String gender;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private String position;
    @NotNull
    private String email;
    private String passwordCredential;
    private BigDecimal salaryAmount;
    private String openPurpose;
    private LocalDate joinWorkDt;
    private String religion;
    private String workingWeb;
    private BigDecimal foodAmount;
    private BigDecimal thr;
    private BigDecimal bonus;
    private BigDecimal ticketAmt;
    private LocalDate ticketBuyDt;
    private String noRekening;
    private LocalDate lastSalaryIncreaseDt;
    private Long branchId;
    private String cuti;

}
