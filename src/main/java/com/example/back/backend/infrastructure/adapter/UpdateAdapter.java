package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.UpdateRepository;
import com.example.back.backend.infrastructure.entity.Member;
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
    private final DaoPayrollJpa daoPayrollJpa;
    private final DaoMemberInfoJpa daoMemberInfoJpa;


    public void updateData(GlobalModel globalModel) throws RuntimeException {

        Member member = daoMemberJpa.findById(globalModel.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

        member.setName(globalModel.getName());
        member.setAddress(globalModel.getAddress());
        member.setGender(globalModel.getGender());
        member.setEmail(globalModel.getEmail());
        member.setBranchId(globalModel.getBranchId());
        member.setUpdDt(LocalDate.now());
        member.setUpdTm(LocalTime.now());

        log.debug("Update Member : [{}]", member);

        daoMemberJpa.save(member);


        MemberInfo memberInfo = daoMemberInfoJpa.findById(globalModel.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

        memberInfo.setPosition(globalModel.getPosition());
        memberInfo.setJoinWorkDt(globalModel.getJoinWorkDt());
        memberInfo.setReligion(globalModel.getReligion());
        memberInfo.setWorkingWeb(globalModel.getWorkingWeb());
        memberInfo.setCuti(globalModel.getCuti());
        memberInfo.setUpdDt(LocalDate.now());
        memberInfo.setUpdTm(LocalTime.now());

        log.debug("Update memberInfo : [{}]", memberInfo);

        daoMemberInfoJpa.save(memberInfo);


        Payroll payroll = daoPayrollJpa.findById(globalModel.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

        payroll.setSalaryAmt(globalModel.getSalaryAmt());
        payroll.setRemark(globalModel.getRemark());
        payroll.setFoodAmount(globalModel.getFoodAmount());
        payroll.setThr(globalModel.getThr());
        payroll.setBonus(globalModel.getBonus());
        payroll.setNoRekening(globalModel.getNoRekening());
        payroll.setLastSalaryIncreaseDt(globalModel.getLastSalaryIncreaseDt());
        payroll.setUpdDt(LocalDate.now());
        payroll.setUpdTm(LocalTime.now());

        log.debug("Update payroll : [{}]", payroll);

        daoPayrollJpa.save(payroll);

    }

    @Override
    public void deleteUser(GlobalModel globalModel) throws Exception {

        Member member = daoMemberJpa.findById(globalModel.getRefNo()).orElseThrow(()-> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

        MemberInfo memberInfo = daoMemberInfoJpa.findById(globalModel.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

        Payroll payroll = daoPayrollJpa.findById(globalModel.getRefNo()).orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

        log.debug("DELETE PROCESS START");

        daoPayrollJpa.deleteById(payroll.getRefNo());
        daoMemberInfoJpa.deleteById(memberInfo.getRefNo());
        daoMemberJpa.deleteById(member.getRefNo());

    }
}
