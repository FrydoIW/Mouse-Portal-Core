package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.common.util.HistoryFactory;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.UpdateRepository;
import com.example.back.backend.infrastructure.entity.Member;
import com.example.back.backend.infrastructure.entity.MemberCredential;
import com.example.back.backend.infrastructure.entity.MemberInfo;
import com.example.back.backend.infrastructure.entity.Payroll;
import com.example.back.backend.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
@Repository
@Slf4j
public class UpdateAdapter implements UpdateRepository {

    private final DaoMemberJpa daoMemberJpa;
    private final DaoHistoryJpa daoHistoryJpa;
    private final DaoPayrollJpa daoPayrollJpa;
    private final DaoMemberInfoJpa daoMemberInfoJpa;
    private final DaoMemberCredentialJpa daoMemberCredentialJpa;

    private final HistoryFactory historyFactory;

    public void updateData(GlobalModel globalModel) throws RuntimeException {

        Member member = daoMemberJpa.findById(globalModel.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.USER_FOUNT.getCode()));

        member.setName(globalModel.getName());
        member.setHisNo(daoHistoryJpa.findMaxHisNo(globalModel.getRefNo()));
        member.setAddress(globalModel.getAddress());
        member.setBirthDate(globalModel.getBirthDate());
        member.setSex(globalModel.getGender());
        member.setEmail(globalModel.getEmail());
        member.setBranchId(globalModel.getBranchId());
        member.setUpdDt(LocalDate.now());
        member.setUpdTm(LocalTime.now());

        log.debug("Update Member : [{}]", member);

        daoMemberJpa.save(member);

        historyFactory.insertToMember(member);

        MemberInfo memberInfo = daoMemberInfoJpa.findById(globalModel.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.USER_FOUNT.getCode()));

        memberInfo.setHisNo(daoHistoryJpa.findMaxHisNo(globalModel.getRefNo()));
        memberInfo.setPosition(globalModel.getPosition());
        memberInfo.setJoinWorkDt(globalModel.getJoinWorkDt());
        memberInfo.setReligion(globalModel.getReligion());
        memberInfo.setWorkingWeb(globalModel.getWorkingWeb());
        memberInfo.setCuti(globalModel.getCuti());
        memberInfo.setUpdDt(LocalDate.now());
        memberInfo.setUpdTm(LocalTime.now());

        log.debug("Update memberInfo : [{}]", memberInfo);

        daoMemberInfoJpa.save(memberInfo);

        historyFactory.insertIntoMemberInfo(memberInfo);

        Payroll payroll = daoPayrollJpa.findById(globalModel.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.USER_FOUNT.getCode()));

        payroll.setTrxAmt(globalModel.getTrxAmt());
        payroll.setFoodAmount(globalModel.getFoodAmount());
        payroll.setThr(globalModel.getThr());
        payroll.setBonus(globalModel.getBonus());
        payroll.setTiketAmt(globalModel.getTicketAmt());
        payroll.setTiketBuyDt(globalModel.getTicketBuyDt());
        payroll.setNoRekening(globalModel.getNoRekening());
        payroll.setLastSalaryIncreaseDt(globalModel.getLastSalaryIncreaseDt());
        payroll.setUpdDt(LocalDate.now());
        payroll.setUpdTm(LocalTime.now());

        log.debug("Update payroll : [{}]", payroll);

        daoPayrollJpa.save(payroll);

        historyFactory.insertIntoPayroll(payroll);

    }

    public void updateStatus(GlobalModel globalModel) throws Exception {


        MemberInfo memberInfo = daoMemberInfoJpa.findById(globalModel.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.USER_FOUNT.getCode()));

        memberInfo.setHisNo(daoHistoryJpa.findMaxHisNo(globalModel.getRefNo()));
        memberInfo.setStatus(globalModel.getStatus());
        memberInfo.setUpdDt(LocalDate.now());
        memberInfo.setUpdTm(LocalTime.now());

        log.debug("Update status memberInfo : [{}]", memberInfo);

        daoMemberInfoJpa.save(memberInfo);

        historyFactory.insertIntoMemberInfo(memberInfo);

        MemberCredential memberCredential = daoMemberCredentialJpa.findByRefNo(globalModel.getRefNo());

        memberCredential.setHisNo(daoHistoryJpa.findMaxHisNo(globalModel.getRefNo()));
        memberCredential.setStatus(globalModel.getStatus());
        memberCredential.setUpdDt(LocalDate.now());
        memberCredential.setUpdTm(LocalTime.now());

        log.debug("Update status memberInfo : [{}]", memberInfo);

        daoMemberCredentialJpa.save(memberCredential);

        historyFactory.insertIntoCredential(memberCredential);

    }

}
