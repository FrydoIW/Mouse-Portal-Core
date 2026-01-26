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
    private String gender;
    private String email;

    //MEMBER_INFO TABLE
    private String position;
    private LocalDate joinWorkDt;
    private String religion;
    private String workingWeb;
    private String cuti;

    //PAYROLL TABLE
    private BigDecimal salaryAmt;
    private String remark;
    private BigDecimal foodAmount;
    private BigDecimal thr;
    private BigDecimal bonus;
    private String noRekening;
    private LocalDate lastSalaryIncreaseDt;

    //REKENING
    private Long rekeningId;
    private Long branchIdRekening;
    private String rekeningNm;
    private String ktpNo;
    private String motherNm;
    private String birthPlace;
    private String homeAddr;
    private String rt;
    private String rw;
    private String kelurahan;
    private String kecamatan;
    private String kabupaten;
    private String province;
    private String genderRekening;
    private LocalDate expiredKtpDt;
    private String rekNo;
    private String pinNo;
    private String atmNo;
    private String rekType;
    private LocalDate atmExpiredDt;
    private String userIdMobile;
    private String passMBanking;
    private String bankNm;
    private String remarkRekening;
    private String noHp;
    private String emailRekening;
    private String passEmail;
    private Integer masaSewaBank;
    private BigDecimal saldo;
    private LocalDate birthDt;
    private String status;
    private byte[] ktpImage;

    //EXPENSE
    private Long expenseId;
    private String expenseName;
    private BigDecimal cost;
    private LocalDate dueDate;
    private String memo;

    //BRANCH
    private Long branchId;
    private String branchName;

    //ADMIN
    private int adminId;
    private String adminName;
    private String adminAddress;
    private LocalDate adminBrithDt;
    private String adminGender;
    private String adminEmail;
    private String adminOldEmail;
    private String adminPasswordHash;
    private String adminTwoFactorSecret;
    private String adminProfilePict;
    private Boolean emailVerification;
    private String verificationToken;

    //WORKSPACE & WORKSPACE INFO
    private String workspaceId;
    private String workspaceHierarchy;
    private String workspaceName;

}
