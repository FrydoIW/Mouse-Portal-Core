package com.example.back.backend.application.dto.atm;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ATM0100AInput {

    Long branchId;
    String nomorRekening;
    String bank;
    String owner;
    BigDecimal amount;

}
