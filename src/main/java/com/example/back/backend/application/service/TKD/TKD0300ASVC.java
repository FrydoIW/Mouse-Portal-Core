package com.example.back.backend.application.service.TKD;

import com.example.back.backend.application.dto.tkd.TKD0300AInput;
import com.example.back.backend.application.dto.tkd.TKD0300AOutput;
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

        ctxSVC.member = daoMemberJpa.findByEmailAndUserMaster(ctxSVC.input.getOldEmail(),"NULL");

        ctxSVC.memberInfo = daoMemberInfoJpa.findById(ctxSVC.member.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.USER_FOUNT.getCode()));

        ctxSVC.payroll = daoPayrollJpa.findById(ctxSVC.member.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.USER_FOUNT.getCode()));

    }

    private void updateData(CtxSVC ctxSVC) throws RuntimeException {

        GlobalModel globalModel = new GlobalModel();

        globalModel.setRefNo(ctxSVC.member.getRefNo());

        // SET MEMBER
        globalModel.setName(getValueOrDefault(
                ctxSVC.input.getName(),
                ctxSVC.member.getName()
        ));
        globalModel.setAddress(getValueOrDefault(
                ctxSVC.input.getAddress(),
                ctxSVC.member.getAddress()
        ));
        globalModel.setBirthDate(getValueOrDefault(
                ctxSVC.input.getBirthDt(),
                ctxSVC.member.getBirthDate()
        ));
        globalModel.setGender(getValueOrDefault(
                ctxSVC.input.getGender(),
                ctxSVC.member.getSex()
        ));
        globalModel.setEmail(getValueOrDefault(
                ctxSVC.input.getEmail(),
                ctxSVC.member.getEmail()
        ));
        globalModel.setBranchId(getValueOrDefault(
                ctxSVC.input.getBranchId(),
                ctxSVC.member.getBranchId()
        ));


        // MEMBER_INFO
        globalModel.setPosition(getValueOrDefault(
                ctxSVC.input.getPosition(),
                ctxSVC.memberInfo.getPosition()
        ));
        globalModel.setJoinWorkDt(getValueOrDefault(
                ctxSVC.input.getJoinWorkDt(),
                ctxSVC.memberInfo.getJoinWorkDt()
        ));
        globalModel.setReligion(getValueOrDefault(
                ctxSVC.input.getReligion(),
                ctxSVC.memberInfo.getReligion()
        ));
        globalModel.setWorkingWeb(getValueOrDefault(
                ctxSVC.input.getWorkingWeb(),
                ctxSVC.memberInfo.getWorkingWeb()
        ));
        globalModel.setCuti(getValueOrDefault(
                ctxSVC.input.getCuti(),
                ctxSVC.memberInfo.getCuti()
        ));

        // PAYROLL
        globalModel.setTrxAmt(getValueOrDefault(
                ctxSVC.input.getSalaryAmount(),
                ctxSVC.payroll.getTrxAmt()
        ));
        globalModel.setFoodAmount(getValueOrDefault(
                ctxSVC.input.getFoodAmount(),
                ctxSVC.payroll.getFoodAmount()
        ));
        globalModel.setThr(getValueOrDefault(
                ctxSVC.input.getThr(),
                ctxSVC.payroll.getThr()
        ));
        globalModel.setBonus(getValueOrDefault(
                ctxSVC.input.getBonus(),
                ctxSVC.payroll.getBonus()
        ));
        globalModel.setTicketAmt(getValueOrDefault(
                ctxSVC.input.getTicketAmt(),
                ctxSVC.payroll.getTiketAmt()
        ));
        globalModel.setTicketBuyDt(getValueOrDefault(
                ctxSVC.input.getTicketBuyDt(),
                ctxSVC.payroll.getTiketBuyDt()
        ));
        globalModel.setNoRekening(getValueOrDefault(
                ctxSVC.input.getNoRekening(),
                ctxSVC.payroll.getNoRekening()
        ));
        globalModel.setLastSalaryIncreaseDt(getValueOrDefault(
                ctxSVC.input.getLastSalaryIncreaseDt(),
                ctxSVC.payroll.getLastSalaryIncreaseDt()
        ));

        updateAdapter.updateData(globalModel);

    }

    private <T> T getValueOrDefault(T inputValue, T defaultValue) {
        if (inputValue == null) {
            return defaultValue;
        }

        if (inputValue instanceof String) {
            String str = (String) inputValue;
            if (str.trim().isEmpty()) {
                return defaultValue;
            }
        }

        if (inputValue instanceof BigDecimal) {
            BigDecimal bd = (BigDecimal) inputValue;
            if (bd.compareTo(BigDecimal.ZERO) == 0) {
                return defaultValue;
            }
        }

        return inputValue;
    }

    private void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("Update Data Success");

    }

}
