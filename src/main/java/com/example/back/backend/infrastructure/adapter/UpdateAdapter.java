package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.Enum.AccountEnum;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.UpdateRepository;
import com.example.back.backend.infrastructure.entity.AuditLog;
import com.example.back.backend.infrastructure.entity.Member;
import com.example.back.backend.infrastructure.entity.MemberInfo;
import com.example.back.backend.infrastructure.entity.Payroll;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import com.example.back.backend.infrastructure.jpa.DaoAuditLog;
import com.example.back.backend.infrastructure.jpa.DaoBranchJpa;
import com.example.back.backend.infrastructure.jpa.DaoMemberInfoJpa;
import com.example.back.backend.infrastructure.jpa.DaoMemberJpa;
import com.example.back.backend.infrastructure.jpa.DaoPayrollJpa;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
@Repository
@Slf4j
public class UpdateAdapter implements UpdateRepository {

    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper()
                    .findAndRegisterModules()
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private final DaoMemberJpa daoMemberJpa;
    private final DaoPayrollJpa daoPayrollJpa;
    private final DaoMemberInfoJpa daoMemberInfoJpa;

    private final DaoAuditLog daoAuditLog;
    private final DaoAdminJpa daoAdminJpa;
    private final DaoBranchJpa branchJpa;

    public void updateData(GlobalModel globalModel) throws RuntimeException {

        try {
            Member member = daoMemberJpa.findById(globalModel.getRefNo())
                    .orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

            String memberFromJson = OBJECT_MAPPER.writeValueAsString(member);

            member.setName(globalModel.getName());
            member.setAddress(globalModel.getAddress());
            member.setGender(globalModel.getGender());
            member.setEmail(globalModel.getEmail());
            member.setBranchId(globalModel.getBranchId());
            member.setUpdDt(LocalDate.now());
            member.setUpdTm(LocalTime.now());

            log.debug("Update Member : [{}]", member);

            daoMemberJpa.save(member);

            String memberToJson = OBJECT_MAPPER.writeValueAsString(member);
            saveAuditUpdate(globalModel,
                    resolveWorkspaceId(globalModel, Math.toIntExact(member.getBranchId())),
                    "member",
                    member.getRefNo(),
                    memberFromJson,
                    memberToJson
            );

            MemberInfo memberInfo = daoMemberInfoJpa.findById(globalModel.getRefNo())
                    .orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

            String memberInfoFromJson = OBJECT_MAPPER.writeValueAsString(memberInfo);

            memberInfo.setPosition(globalModel.getPosition());
            memberInfo.setJoinWorkDt(globalModel.getJoinWorkDt());
            memberInfo.setReligion(globalModel.getReligion());
            memberInfo.setWorkingWeb(globalModel.getWorkingWeb());
            memberInfo.setCuti(globalModel.getCuti());
            memberInfo.setUpdDt(LocalDate.now());
            memberInfo.setUpdTm(LocalTime.now());

            log.debug("Update memberInfo : [{}]", memberInfo);

            daoMemberInfoJpa.save(memberInfo);

            String memberInfoToJson = OBJECT_MAPPER.writeValueAsString(memberInfo);
            saveAuditUpdate(globalModel,
                    resolveWorkspaceId(globalModel, Math.toIntExact(member.getBranchId())),
                    "member_info",
                    memberInfo.getRefNo(),
                    memberInfoFromJson,
                    memberInfoToJson
            );

            Payroll payroll = daoPayrollJpa.findById(globalModel.getRefNo())
                    .orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

            String payrollFromJson = OBJECT_MAPPER.writeValueAsString(payroll);

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

            String payrollToJson = OBJECT_MAPPER.writeValueAsString(payroll);
            saveAuditUpdate(globalModel,
                    resolveWorkspaceId(globalModel, Math.toIntExact(member.getBranchId())),
                    "payroll",
                    payroll.getRefNo(),
                    payrollFromJson,
                    payrollToJson
            );

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(GlobalModel globalModel) throws Exception {

        try {
            Member member = daoMemberJpa.findById(globalModel.getRefNo())
                    .orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

            MemberInfo memberInfo = daoMemberInfoJpa.findById(globalModel.getRefNo())
                    .orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

            Payroll payroll = daoPayrollJpa.findById(globalModel.getRefNo())
                    .orElseThrow(() -> new RuntimeException(SysErrCode.ACCOUNT_NOT_FOUND.getCode()));

            String memberFromJson = OBJECT_MAPPER.writeValueAsString(member);
            String memberInfoFromJson = OBJECT_MAPPER.writeValueAsString(memberInfo);
            String payrollFromJson = OBJECT_MAPPER.writeValueAsString(payroll);

            String workspaceId = resolveWorkspaceId(globalModel, Math.toIntExact(member.getBranchId()));

            log.debug("DELETE PROCESS START");

            daoPayrollJpa.deleteById(payroll.getRefNo());
            daoMemberInfoJpa.deleteById(memberInfo.getRefNo());
            daoMemberJpa.deleteById(member.getRefNo());

            saveAuditDelete(globalModel, workspaceId, "payroll", payroll.getRefNo(), payrollFromJson);
            saveAuditDelete(globalModel, workspaceId, "member_info", memberInfo.getRefNo(), memberInfoFromJson);
            saveAuditDelete(globalModel, workspaceId, "member", member.getRefNo(), memberFromJson);

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void saveAuditUpdate(GlobalModel gm,
                                 String workspaceId,
                                 String tableName,
                                 String pkValue,
                                 String fromJson,
                                 String toJson) {

        AuditLog audit = new AuditLog();
        audit.setTableName(tableName);
        audit.setPkValue(pkValue);
        audit.setAction(AccountEnum.HistoryType.UPDATE.getValue());
        audit.setChangedAt(LocalDateTime.now());
        audit.setChangedBy(gm.getAdminId());
        audit.setChangedByName(daoAdminJpa.getAdminName(gm.getAdminId()));
        audit.setWorkspaceId(workspaceId);
        audit.setChangesJson("{\"from\":" + fromJson + ",\"to\":" + toJson + "}");

        daoAuditLog.save(audit);
    }

    private void saveAuditDelete(GlobalModel gm,
                                 String workspaceId,
                                 String tableName,
                                 String pkValue,
                                 String fromJson) {

        AuditLog audit = new AuditLog();
        audit.setTableName(tableName);
        audit.setPkValue(pkValue);
        audit.setAction(AccountEnum.HistoryType.DELETE.getValue());
        audit.setChangedAt(LocalDateTime.now());
        audit.setChangedBy(gm.getAdminId());
        audit.setChangedByName(daoAdminJpa.getAdminName(gm.getAdminId()));
        audit.setWorkspaceId(workspaceId);
        audit.setChangesJson("{\"from\":" + fromJson + ",\"to\":null}");

        daoAuditLog.save(audit);
    }

    private String resolveWorkspaceId(GlobalModel input, Integer branchId) {
        if (input.getWorkspaceId() != null && !input.getWorkspaceId().isBlank()) {
            return input.getWorkspaceId();
        }
        if (branchId != null) {
            return branchJpa.getWorkspaceIdByBranchId(Math.toIntExact(branchId.longValue()));
        }
        return null;
    }
}