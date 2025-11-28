package com.example.back.backend.application.service;

import com.example.back.backend.application.dto.TKD0500AInput;
import com.example.back.backend.application.dto.TKD0500AOutput;
import com.example.back.backend.common.Enum.AccountEnum;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.infrastructure.adapter.UpdateAdapter;
import com.example.back.backend.infrastructure.entity.Member;
import com.example.back.backend.infrastructure.entity.MemberInfo;
import com.example.back.backend.infrastructure.entity.Payroll;
import com.example.back.backend.infrastructure.jpa.DaoMemberInfoJpa;
import com.example.back.backend.infrastructure.jpa.DaoMemberJpa;
import com.example.back.backend.infrastructure.jpa.DaoPayrollJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TKD0500ASVC {

    private final DaoMemberJpa daoMemberJpa;
    private final DaoMemberInfoJpa daoMemberInfoJpa;
    private final DaoPayrollJpa daoPayrollJpa;
    private final UpdateAdapter updateAdapter;

    public class CtxSVC {

        TKD0500AInput input;
        TKD0500AOutput output;

        Member member;
        MemberInfo memberInfo;
        Payroll payroll;

    }

    public TKD0500AOutput execute(TKD0500AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0500AOutput();

        checkInputData(ctxSVC);
        updateData(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void checkInputData(CtxSVC ctxSVC) throws RuntimeException {

        ctxSVC.member = daoMemberJpa.findMemberByEmail(ctxSVC.input.getEmail());

        ctxSVC.memberInfo = daoMemberInfoJpa.findById(ctxSVC.member.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.USER_FOUNT.getCode()));

        ctxSVC.payroll = daoPayrollJpa.findById(ctxSVC.member.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.USER_FOUNT.getCode()));

    }

    private void updateData(CtxSVC ctxSVC) throws RuntimeException {

        GlobalModel globalModel = new GlobalModel();

        globalModel.setRefNo(ctxSVC.member.getRefNo());
        globalModel.setName(ctxSVC.input.getName());
        globalModel.setAddress(ctxSVC.input.getAddress());
        globalModel.setBirthDate(ctxSVC.input.getBirthDt());
        globalModel.setGender(ctxSVC.input.getGender());
        globalModel.setEmail(ctxSVC.input.getEmail());
        globalModel.setPosition(ctxSVC.input.getPosition());
        globalModel.setTrxAmt(ctxSVC.input.getSalaryAmt());

        updateAdapter.updateData(globalModel);

    }

    private void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("Update Data Success");

    }

}
