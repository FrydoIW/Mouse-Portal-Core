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
@Table(name = "MEMBER")
@ToString
public class Member {

    @Id
    private String refNo;
    private String name;
    private String address;
    private LocalDate birthDate;
    private String sex;
    private String email;
    private Integer hisNo;
    private LocalDate regDt;
    private LocalTime regTm;
    private LocalDate updDt;
    private LocalTime updTm;

}
