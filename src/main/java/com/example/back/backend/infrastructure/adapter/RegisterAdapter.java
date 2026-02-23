package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.Enum.AccountEnum;
import com.example.back.backend.common.util.*;
import com.example.back.backend.domain.model.*;
import com.example.back.backend.domain.repository.RegisterRepository;
import com.example.back.backend.infrastructure.entity.*;
import com.example.back.backend.infrastructure.jpa.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
@Repository
@Slf4j
public class RegisterAdapter implements RegisterRepository {

    private final DaoMemberJpa memberJpa;
    private final DaoMemberInfoJpa memberInfoJpa;
    private final DaoPayrollJpa payrollJpa;
    private final DaoAuditLog daoAuditLog;
    private final DaoAdminJpa daoAdminJpa;
    private final DaoBranchJpa branchJpa;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

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

        log.debug("Insert Member : [{}]", member);

        Member savedMember = memberJpa.saveAndFlush(member);

        AuditLog audit = new AuditLog();
        audit.setTableName("member");
        audit.setPkValue(savedMember.getRefNo());
        audit.setAction(AccountEnum.HistoryType.INSERT.getValue());
        audit.setChangeAt(LocalDateTime.now());
        audit.setChangedBy(input.getAdminId());
        audit.setChangedByName(daoAdminJpa.getAdminName(input.getAdminId()));
        audit.setWorkspaceId(resolveWorkspaceId(input)); // ✅ workspace id
        String toJson = OBJECT_MAPPER.writeValueAsString(savedMember);
        audit.setChangeJson("{\"from\":null,\"to\":" + toJson + "}");
        daoAuditLog.save(audit);
    }

    private void registerMemberInfo(GlobalModel input) throws Exception {

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

        log.debug("Insert Member Info : [{}]", memberInfo);

        MemberInfo savedInfo = memberInfoJpa.saveAndFlush(memberInfo);

        AuditLog audit = new AuditLog();
        audit.setTableName("member_info");
        audit.setPkValue(savedInfo.getRefNo());
        audit.setAction(AccountEnum.HistoryType.INSERT.getValue());
        audit.setChangeAt(LocalDateTime.now());
        audit.setChangedBy(input.getAdminId());
        audit.setChangedByName(daoAdminJpa.getAdminName(input.getAdminId()));
        audit.setWorkspaceId(resolveWorkspaceId(input));
        String toJson = OBJECT_MAPPER.writeValueAsString(savedInfo);
        audit.setChangeJson("{\"from\":null,\"to\":" + toJson + "}");
        daoAuditLog.save(audit);
    }

    private void registerPayroll(GlobalModel input) throws Exception {

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

        log.debug("Insert payroll : [{}]", payroll);

        Payroll savedPayroll = payrollJpa.saveAndFlush(payroll);

        AuditLog audit = new AuditLog();
        audit.setTableName("payroll");
        audit.setPkValue(savedPayroll.getRefNo());
        audit.setAction(AccountEnum.HistoryType.INSERT.getValue());
        audit.setChangeAt(LocalDateTime.now());
        audit.setChangedBy(input.getAdminId());
        audit.setChangedByName(daoAdminJpa.getAdminName(input.getAdminId()));
        audit.setWorkspaceId(resolveWorkspaceId(input));
        String toJson = OBJECT_MAPPER.writeValueAsString(savedPayroll);
        audit.setChangeJson("{\"from\":null,\"to\":" + toJson + "}");
        daoAuditLog.save(audit);
    }

    private String resolveWorkspaceId(GlobalModel input) {
        if (input.getWorkspaceId() != null && !input.getWorkspaceId().isBlank()) {
            return input.getWorkspaceId();
        }
        if (input.getBranchId() != null) {
            return branchJpa.getWorkspaceIdByBranchId(Math.toIntExact(input.getBranchId()));
        }
        return null;
    }
}