package com.example.back.backend.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEMBER_INFO")
@ToString
public class MemberInfo {

    @Id
    private String refNo;
    private String status;
    private String position;
    private Integer hisNo;
    private String religion;
    private String workingWeb;
    private LocalDate joinWorkDt;
    private LocalDate regDt;
    private LocalTime regTm;
    private LocalDate updDt;
    private LocalTime updTm;

}
