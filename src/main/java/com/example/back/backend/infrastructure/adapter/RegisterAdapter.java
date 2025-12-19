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
    private final DaoHistoryJpa historyJpa;
    private final DaoMemberInfoJpa memberInfoJpa;
    private final DaoMemberCredentialJpa credentialJpa;
    private final DaoPayrollJpa payrollJpa;
    private final HistoryFactory historyFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertMember(GlobalModel input) throws Exception {

        registerMember(input);
        registerMemberInfo(input);
        registerCredential(input);
        registerPayroll(input);

    }

    private void registerMember(GlobalModel input) throws Exception {

        MemberModel memberModel = new MemberModel();

        memberModel.setAddress(input.getAddress());
        memberModel.setGender(input.getGender());
        memberModel.setName(input.getName());
        memberModel.setBirthDate(input.getBirthDate());
        memberModel.setRefNo(input.getRefNo());
        memberModel.setEmail(input.getEmail());

        Member member = MemberMapper.toMemberEntity(memberModel);

        member.setHisNo(historyJpa.findMaxHisNo(member.getRefNo()));
        member.setRegDt(LocalDate.now());
        member.setRegTm(LocalTime.now());
        member.setUpdDt(LocalDate.now());
        member.setUpdTm(LocalTime.now());

        log.debug("Insert Member : [{}]",member);

        memberJpa.save(member);

        historyFactory.insertToMember(member);

    }

    private void registerMemberInfo(GlobalModel input) throws Exception{

        MemberInfoModel infoModel = new MemberInfoModel();

        infoModel.setPosition(input.getPosition());
        infoModel.setStatus(input.getStatus());
        infoModel.setRefNo(input.getRefNo());

        MemberInfo memberInfo = MemberInfoMapper.toMemberInfoEntity(infoModel);

        memberInfo.setHisNo(historyJpa.findMaxHisNo(memberInfo.getRefNo()));
        memberInfo.setRegDt(LocalDate.now());
        memberInfo.setRegTm(LocalTime.now());
        memberInfo.setUpdDt(LocalDate.now());
        memberInfo.setUpdTm(LocalTime.now());

        log.debug("Insert Member Info : [{}]",memberInfo);

        memberInfoJpa.save(memberInfo);

        historyFactory.insertIntoMemberInfo(memberInfo);

    }

    private void registerCredential(GlobalModel input) throws Exception{

        if(input.getPasswordHash() == null) return;

        MemberCredentialModel credentialModel = new MemberCredentialModel();

        credentialModel.setPasswordHash(input.getPasswordHash());
        credentialModel.setRole(input.getPosition());
        credentialModel.setStatus(input.getStatus());
        credentialModel.setRefNo(input.getRefNo());
        credentialModel.setTwoFactorSecret(input.getTwoFactorSecret());

        MemberCredential memberCredential = CredentialMapper.toMemberCredentialEntity(credentialModel);

        memberCredential.setHisNo(historyJpa.findMaxHisNo(input.getRefNo()));
        memberCredential.setRegDt(LocalDate.now());
        memberCredential.setRegTm(LocalTime.now());
        memberCredential.setUpdDt(LocalDate.now());
        memberCredential.setUpdTm(LocalTime.now());

        log.debug("Insert memberCredential : [{}]",memberCredential);

        credentialJpa.save(memberCredential);

        historyFactory.insertIntoCredential(memberCredential);

    }

    private void registerPayroll(GlobalModel input) throws Exception{

        PayrollModel payrollModel = new PayrollModel();

        payrollModel.setRefNo(input.getRefNo());
        payrollModel.setTrxAmt(input.getTrxAmt());
        payrollModel.setRemark(input.getPayrollRemark());

        Payroll payroll = PayrollMapper.toPayrollEntity(payrollModel);

        payroll.setHisNo(historyJpa.findMaxHisNo(input.getRefNo()));
        payroll.setRegDt(LocalDate.now());
        payroll.setRegTm(LocalTime.now());
        payroll.setUpdDt(LocalDate.now());
        payroll.setUpdTm(LocalTime.now());

        log.debug("Insert payroll : [{}]",payroll);

        payrollJpa.save(payroll);

        historyFactory.insertIntoPayroll(payroll);

    }
}

