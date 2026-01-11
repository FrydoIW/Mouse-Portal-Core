package com.example.back.backend.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class RekeningModel {

    private Long branchId;
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
    private String gender;
    private LocalDate expiredKtpDt;
    private String rekNo;
    private String pinNo;
    private String atmNo;
    private String rekType;
    private LocalDate atmExpiredDt;
    private String userIdMobile;
    private String passMBanking;
    private String bankNm;
    private String remark;
    private String noHp;
    private String email;
    private String passEmail;
    private Integer masaSewaBank;
    private BigDecimal saldo;
    private LocalDate birthDt;
    private String status;

}
