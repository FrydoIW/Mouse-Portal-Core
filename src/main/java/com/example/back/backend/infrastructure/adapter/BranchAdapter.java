package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.Enum.AccountEnum;
import com.example.back.backend.common.util.BranchMapper;
import com.example.back.backend.domain.model.BranchModel;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.BranchRepository;
import com.example.back.backend.infrastructure.entity.AuditLog;
import com.example.back.backend.infrastructure.entity.Branch;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import com.example.back.backend.infrastructure.jpa.DaoAuditLog;
import com.example.back.backend.infrastructure.jpa.DaoBranchJpa;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
@Slf4j
public class BranchAdapter implements BranchRepository {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final DaoBranchJpa branchJpa;

    private final DaoAuditLog daoAuditLog;
    private final DaoAdminJpa daoAdminJpa;

    @Override
    @Transactional
    public void insertBranchInfo(GlobalModel globalModel) throws Exception {

        BranchModel branchModel = new BranchModel();
        branchModel.setBranchName(globalModel.getBranchName());
        branchModel.setWorkspaceId(globalModel.getWorkspaceId());

        Branch branch = BranchMapper.toBranchEntity(branchModel);
        branch.setRegDt(LocalDate.now());
        branch.setUpdDt(LocalDate.now());

        log.debug("Register Branch : [{}]", branch);

        Branch savedBranch = branchJpa.saveAndFlush(branch);

        AuditLog audit = new AuditLog();
        audit.setTableName("branch");
        audit.setPkValue(String.valueOf(savedBranch.getBranchId()));
        audit.setAction(AccountEnum.HistoryType.INSERT.getValue());
        audit.setChangeAt(LocalDateTime.now());
        audit.setChangedBy(globalModel.getAdminId());
        audit.setChangedByName(daoAdminJpa.getAdminName(globalModel.getAdminId()));
        audit.setWorkspaceId(savedBranch.getWorkspaceId());

        String toJson = OBJECT_MAPPER.writeValueAsString(savedBranch);
        audit.setChangeJson("{\"from\":null,\"to\":" + toJson + "}");

        daoAuditLog.save(audit);
    }

    @Override
    @Transactional
    public void editBranch(GlobalModel globalModel) throws Exception {

        Branch branch = branchJpa.findById(globalModel.getBranchId())
                .orElseThrow(() -> new Exception("Data Not Found"));

        String fromJson = OBJECT_MAPPER.writeValueAsString(branch);

        branch.setBranchName(globalModel.getBranchName());
        branch.setUpdDt(LocalDate.now());

        log.debug("Edit Branch : [{}]", branch);

        Branch savedBranch = branchJpa.saveAndFlush(branch);

        String toJson = OBJECT_MAPPER.writeValueAsString(savedBranch);

        AuditLog audit = new AuditLog();
        audit.setTableName("branch");
        audit.setPkValue(String.valueOf(savedBranch.getBranchId()));
        audit.setAction(AccountEnum.HistoryType.UPDATE.getValue());
        audit.setChangeAt(LocalDateTime.now());
        audit.setChangedBy(globalModel.getAdminId());
        audit.setChangedByName(daoAdminJpa.getAdminName(globalModel.getAdminId()));
        audit.setWorkspaceId(savedBranch.getWorkspaceId());

        audit.setChangeJson("{\"from\":" + fromJson + ",\"to\":" + toJson + "}");

        daoAuditLog.save(audit);
    }
}