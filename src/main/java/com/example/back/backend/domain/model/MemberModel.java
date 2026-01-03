package com.example.back.backend.domain.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberModel {

    private String refNo;
    private String name;
    private String address;
    private String gender;
    private String email;
    private Long branchId;


}
