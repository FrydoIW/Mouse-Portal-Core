package com.example.back.backend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rekening")
@ToString
public class Rekening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "BRANCH_ID")
    private Long branchId;

    @Column(name = "REKENING_NM", nullable = false, length = 100)
    private String rekeningNm;

    @Column(name = "KTP_NO", nullable = false, unique = true, length = 20)
    private String ktpNo;

    @Column(name = "MOTHER_NM", nullable = false, length = 100)
    private String motherNm;

    @Column(name = "BIRTH_PLACE", length = 50)
    private String birthPlace;

    @Column(name = "HOME_ADDR", nullable = false, columnDefinition = "TEXT")
    private String homeAddr;

    @Column(name = "RT", length = 3)
    private String rt;

    @Column(name = "RW", length = 3)
    private String rw;

    @Column(name = "KELURAHAN", length = 50)
    private String kelurahan;

    @Column(name = "KECAMATAN", length = 50)
    private String kecamatan;

    @Column(name = "KABUPATEN", length = 50)
    private String kabupaten;

    @Column(name = "PROVINCE", length = 50)
    private String province;

    @Column(name = "GENDER", length = 20)
    private String gender;

    @Column(name = "EXPIRED_KTP_DT", nullable = false)
    private LocalDate expiredKtpDt;

    @Column(name = "REK_NO", nullable = false, unique = true, length = 20)
    private String rekNo;

    @Column(name = "PIN_NO", nullable = false, length = 6)
    private String pinNo;

    @Column(name = "ATM_NO", nullable = false, unique = true, length = 16)
    private String atmNo;

    @Column(name = "REK_TYPE", nullable = false, length = 20)
    private String rekType;

    @Column(name = "ATM_EXPIRED_DT", nullable = false)
    private LocalDate atmExpiredDt;

    @Column(name = "USER_ID_MOBILE", length = 30)
    private String userIdMobile;

    @Column(name = "PASS_M_BANKING", length = 255)
    private String passMBanking;

    @Column(name = "BANK_NM", nullable = false, length = 50)
    private String bankNm;

    @Column(name = "REMARK", columnDefinition = "TEXT")
    private String remark;

    @Column(name = "NO_HP", length = 15)
    private String noHp;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "PASS_EMAIL", length = 255)
    private String passEmail;

    @Column(name = "MASA_SEWA_BANK")
    private Integer masaSewaBank;

    @Column(name = "REG_DT")
    private LocalDate regDt;

    @Column(name = "UPD_DT")
    private LocalDate updDt;

    @Column(name = "SALDO")
    private BigDecimal saldo;

    @Column(name = "BIRTH_DT")
    private LocalDate birthDt;

    @Column(name = "STATUS")
    private String status;

}