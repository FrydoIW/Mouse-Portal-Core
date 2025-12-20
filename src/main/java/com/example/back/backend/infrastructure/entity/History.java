package com.example.back.backend.infrastructure.entity;

import com.example.back.backend.infrastructure.entity.PK.HistoryPk;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(HistoryPk.class)
@Table(name = "HISTORY")
@ToString
public class History {

    @Id
    private String hisType;
    @Id
    private Integer hisNo;
    @Id
    private String refNo;
    private String name;
    private String address;
    private LocalDate birthDate;
    private String sex;
    private BigDecimal trxAmt;
    private String remark;
    private String status;
    private String position;
    private String passwordHash;
    private String twoFactorSecret;
    private String typeDesc;
    private String email;
    private LocalDate joinWorkDt;
    private String religion;
    private String workingWeb;
    private BigDecimal foodAmount;
    private BigDecimal thr;
    private BigDecimal bonus;
    private BigDecimal tiketAmt;
    private LocalDate tiketBuyDt;
    private String noRekening;
    private LocalDate lastSalaryIncreaseDt;
    private LocalDate regDt;
    private LocalTime regTm;
    private LocalDate updDt;
    private LocalTime updTm;

}
