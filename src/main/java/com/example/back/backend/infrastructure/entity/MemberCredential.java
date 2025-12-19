package com.example.back.backend.infrastructure.entity;

import com.example.back.backend.infrastructure.entity.PK.MemberCredentialPk;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(MemberCredentialPk.class)
@Table(name = "MEMBER_CREDENTIAL")
@Entity
@ToString
public class MemberCredential {

    @Id
    private String refNo;
    private String passwordHash;
    private String twoFactorSecret;
    private String role;
    private String status;
    private int hisNo;
    private LocalDate regDt;
    private LocalTime regTm;
    private LocalDate updDt;
    private LocalTime updTm;

}
