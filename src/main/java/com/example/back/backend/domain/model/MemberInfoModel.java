package com.example.back.backend.domain.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberInfoModel {

    String refNo;
    String position;
    private LocalDate joinWorkDt;
    private String religion;
    private String workingWeb;
    private String cuti;

}
