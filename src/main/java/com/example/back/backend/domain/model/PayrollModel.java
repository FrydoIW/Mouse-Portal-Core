package com.example.back.backend.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class PayrollModel {

    BigDecimal trxAmt = BigDecimal.ZERO;
    String refNo;
    String remark;


}
