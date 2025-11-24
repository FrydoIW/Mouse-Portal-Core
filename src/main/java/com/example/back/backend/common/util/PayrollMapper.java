package com.example.back.backend.common.util;

import com.example.back.backend.domain.model.MemberModel;
import com.example.back.backend.domain.model.PayrollModel;
import com.example.back.backend.infrastructure.entity.Member;
import com.example.back.backend.infrastructure.entity.Payroll;

public class PayrollMapper {

    public static Payroll toPayrollEntity(PayrollModel m) {

        Payroll payroll = new Payroll();

        payroll.setRefNo(m.getRefNo());
        payroll.setTrxAmt(m.getTrxAmt());
        payroll.setRemark(m.getRemark());

        return payroll;
    }

}
