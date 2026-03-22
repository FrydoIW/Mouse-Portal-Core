package com.example.back.backend.application.dto.tkd;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class TKD0300AInput {

    // PK INFO
    String refNo;

    //MEMBER
    String name;
    String address;
    String gender;
    String email;
    Long branchId;

    // MEMBER_INFO
    String position;
    private LocalDate joinWorkDt;
    private String religion;
    private String workingWeb;
    private String cuti;

    // PAYROLL
    private BigDecimal salaryAmt;
    private String remark;
    private BigDecimal foodAmount;
    private BigDecimal thr;
    private BigDecimal bonus;
    private String noRekening;
    private LocalDate lastSalaryIncreaseDt;

    // UPDATE BY
    private int adminEntry;
}
