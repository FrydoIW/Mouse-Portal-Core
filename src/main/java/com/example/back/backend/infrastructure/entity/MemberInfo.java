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
@Table(name = "member_info")
@ToString
public class MemberInfo {

    @Id
    private String refNo;
    private String position;
    private LocalDate regDt;
    private LocalTime regTm;
    private LocalDate updDt;
    private LocalTime updTm;
    private LocalDate joinWorkDt;
    private String religion;
    private String workingWeb;
    private String cuti;


}
