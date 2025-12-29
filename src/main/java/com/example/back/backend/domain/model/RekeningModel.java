package com.example.back.backend.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class RekeningModel {

    private String nomorRekening;
    private String bank;
    private String owner;
    private BigDecimal amount;
    private Long branchId;

}
