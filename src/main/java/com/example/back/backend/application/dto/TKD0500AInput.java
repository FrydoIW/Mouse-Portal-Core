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
    @NotNull
    String email;

    // MEMBER_INFO
    String position;

    // PAYROLL
    BigDecimal salaryAmt;

}
