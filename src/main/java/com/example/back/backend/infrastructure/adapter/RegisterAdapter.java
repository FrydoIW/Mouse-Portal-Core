package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.util.*;
import com.example.back.backend.domain.model.*;
import com.example.back.backend.domain.repository.RegisterRepository;
import com.example.back.backend.infrastructure.entity.*;
import com.example.back.backend.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
@Repository
@Slf4j
public class RegisterAdapter implements RegisterRepository {

    private final DaoMemberJpa memberJpa;
    private final DaoMemberInfoJpa memberInfoJpa;
    private final DaoPayrollJpa payrollJpa;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertMember(GlobalModel input) throws Exception {

        registerMember(input);
        registerMemberInfo(input);
        registerPayroll(input);

    }

    private void registerMember(GlobalModel input) throws Exception {

        MemberModel memberModel = new MemberModel();

        memberModel.setRefNo(input.getRefNo());
        memberModel.setName(input.getName());
        memberModel.setAddress(input.getAddress());
        memberModel.setGender(input.getGender());
        memberModel.setEmail(input.getEmail());
        memberModel.setBranchId(input.getBranchId());

        Member member = MemberMapper.toMemberEntity(memberModel);

        member.setRegDt(LocalDate.now());
        member.setRegTm(LocalTime.now());
        member.setUpdDt(LocalDate.now());
        member.setUpdTm(LocalTime.now());

        log.debug("Insert Member : [{}]",member);

        memberJpa.save(member);

    }

    private void registerMemberInfo(GlobalModel input) throws Exception{

        MemberInfoModel infoModel = new MemberInfoModel();

        infoModel.setRefNo(input.getRefNo());
        infoModel.setPosition(input.getPosition());
        infoModel.setJoinWorkDt(input.getJoinWorkDt());
        infoModel.setReligion(input.getReligion());
        infoModel.setWorkingWeb(input.getWorkingWeb());
        infoModel.setCuti(input.getCuti());

        MemberInfo memberInfo = MemberInfoMapper.toMemberInfoEntity(infoModel);

        memberInfo.setRegDt(LocalDate.now());
        memberInfo.setRegTm(LocalTime.now());
        memberInfo.setUpdDt(LocalDate.now());
        memberInfo.setUpdTm(LocalTime.now());

        log.debug("Insert Member Info : [{}]",memberInfo);

        memberInfoJpa.save(memberInfo);
    }

    private void registerPayroll(GlobalModel input) throws Exception{

        PayrollModel payrollModel = new PayrollModel();

        payrollModel.setRefNo(input.getRefNo());
        payrollModel.setSalaryAmt(input.getSalaryAmt());
        payrollModel.setRemark(input.getRemark());
        payrollModel.setFoodAmount(input.getFoodAmount());
        payrollModel.setThr(input.getThr());
        payrollModel.setBonus(input.getBonus());
        payrollModel.setNoRekening(input.getNoRekening());
        payrollModel.setLastSalaryIncreaseDt(input.getLastSalaryIncreaseDt());

        Payroll payroll = PayrollMapper.toPayrollEntity(payrollModel);

        payroll.setRegDt(LocalDate.now());
        payroll.setRegTm(LocalTime.now());
        payroll.setUpdDt(LocalDate.now());
        payroll.setUpdTm(LocalTime.now());

        log.debug("Insert payroll : [{}]",payroll);

        payrollJpa.save(payroll);
    }
}

