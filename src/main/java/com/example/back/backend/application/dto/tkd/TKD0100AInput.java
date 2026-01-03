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

    //FOR ALL
    private Long branchId;

    // FOR MEMBER
    private String name;
    private String address;
    private String gender;
    private String email;

    // FOR MEMBER_INFO
    private String position;
    private LocalDate joinWorkDt;
    private String religion;
    private String workingWeb;
    private String cuti;

    // FOR PAYROLL
    private BigDecimal salaryAmt;
    private String remark;
    private BigDecimal foodAmount;
    private BigDecimal thr;
    private BigDecimal bonus;
    private String noRekening;
    private LocalDate lastSalaryIncreaseDt;

}
