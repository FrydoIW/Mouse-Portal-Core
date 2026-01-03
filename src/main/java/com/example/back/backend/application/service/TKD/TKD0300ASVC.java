package com.example.back.backend.application.service.TKD;

import com.example.back.backend.application.dto.tkd.TKD0300AInput;
import com.example.back.backend.application.dto.tkd.TKD0300AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.common.util.CompareUtil;
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

import java.math.BigDecimal;

/**
 * @fileName : TKD0500ASVC
 * @author   : dodocool
 * @description : Update data / Update Member Process 🗿
 */


@Slf4j
@RequiredArgsConstructor
@Service
public class TKD0300ASVC {

    private final DaoMemberJpa daoMemberJpa;
    private final DaoMemberInfoJpa daoMemberInfoJpa;
    private final DaoPayrollJpa daoPayrollJpa;
    private final UpdateAdapter updateAdapter;

    public class CtxSVC {

        TKD0300AInput input;
        TKD0300AOutput output;

        Member member;
        MemberInfo memberInfo;
        Payroll payroll;

    }

    public TKD0300AOutput execute(TKD0300AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0300AOutput();

        checkInputData(ctxSVC);
        updateData(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void checkInputData(CtxSVC ctxSVC) throws RuntimeException {

        ctxSVC.member = daoMemberJpa.findById(ctxSVC.input.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

        ctxSVC.memberInfo = daoMemberInfoJpa.findById(ctxSVC.member.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

        ctxSVC.payroll = daoPayrollJpa.findById(ctxSVC.member.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

    }

    private void updateData(CtxSVC ctxSVC) throws RuntimeException {

        GlobalModel globalModel = new GlobalModel();

        //FOR GLOBAL
        globalModel.setRefNo(ctxSVC.input.getRefNo());

        globalModel.setBranchId(CompareUtil.getValueOrDefault(
                ctxSVC.input.getBranchId(),
                ctxSVC.member.getBranchId()
        ));

        // SET MEMBER
        globalModel.setName(CompareUtil.getValueOrDefault(
                ctxSVC.input.getName(),
                ctxSVC.member.getName()
        ));
        globalModel.setAddress(CompareUtil.getValueOrDefault(
                ctxSVC.input.getAddress(),
                ctxSVC.member.getAddress()
        ));
        globalModel.setGender(CompareUtil.getValueOrDefault(
                ctxSVC.input.getGender(),
                ctxSVC.member.getAddress()
        ));
        globalModel.setEmail(CompareUtil.getValueOrDefault(
                ctxSVC.input.getEmail(),
                ctxSVC.member.getEmail()
        ));

        // MEMBER_INFO
        globalModel.setPosition(CompareUtil.getValueOrDefault(
                ctxSVC.input.getPosition(),
                ctxSVC.memberInfo.getPosition()
        ));
        globalModel.setJoinWorkDt(CompareUtil.getValueOrDefault(
                ctxSVC.input.getJoinWorkDt(),
                ctxSVC.memberInfo.getJoinWorkDt()
        ));
        globalModel.setReligion(CompareUtil.getValueOrDefault(
                ctxSVC.input.getReligion(),
                ctxSVC.memberInfo.getReligion()
        ));
        globalModel.setWorkingWeb(CompareUtil.getValueOrDefault(
                ctxSVC.input.getWorkingWeb(),
                ctxSVC.memberInfo.getWorkingWeb()
        ));
        globalModel.setCuti(CompareUtil.getValueOrDefault(
                ctxSVC.input.getCuti(),
                ctxSVC.memberInfo.getCuti()
        ));

        // PAYROLL
        globalModel.setSalaryAmt(CompareUtil.getValueOrDefault(
                ctxSVC.input.getSalaryAmt(),
                ctxSVC.payroll.getSalaryAmt()
        ));
        globalModel.setRemark(CompareUtil.getValueOrDefault(
                ctxSVC.input.getRemark(),
                ctxSVC.payroll.getRemark()
        ));
        globalModel.setFoodAmount(CompareUtil.getValueOrDefault(
                ctxSVC.input.getFoodAmount(),
                ctxSVC.payroll.getFoodAmount()
        ));
        globalModel.setThr(CompareUtil.getValueOrDefault(
                ctxSVC.input.getThr(),
                ctxSVC.payroll.getThr()
        ));
        globalModel.setBonus(CompareUtil.getValueOrDefault(
                ctxSVC.input.getBonus(),
                ctxSVC.payroll.getBonus()
        ));
        globalModel.setNoRekening(CompareUtil.getValueOrDefault(
                ctxSVC.input.getNoRekening(),
                ctxSVC.payroll.getNoRekening()
        ));
        globalModel.setLastSalaryIncreaseDt(CompareUtil.getValueOrDefault(
                ctxSVC.input.getLastSalaryIncreaseDt(),
                ctxSVC.payroll.getLastSalaryIncreaseDt()
        ));

        updateAdapter.updateData(globalModel);

    }

    private void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("Update Data Success");

    }

}
