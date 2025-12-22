package com.example.back.backend.application.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ATM0300AInput {

    Long id;
    String nomorRekening;
    String bank;
    String owner;
    BigDecimal amount;

}
