package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.Enum.AccountEnum;
import com.example.back.backend.common.util.WorkspaceInfoMapper;
import com.example.back.backend.common.util.WorkspaceMapper;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.model.WorkspaceInfoModel;
import com.example.back.backend.domain.model.WorkspaceModel;
import com.example.back.backend.domain.repository.WorkspaceRepository;
import com.example.back.backend.infrastructure.entity.AuditLog;
import com.example.back.backend.infrastructure.entity.Workspace;
import com.example.back.backend.infrastructure.entity.WorkspaceInfo;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import com.example.back.backend.infrastructure.jpa.DaoAuditLog;
import com.example.back.backend.infrastructure.jpa.DaoWorkspaceInfoJpa;
import com.example.back.backend.infrastructure.jpa.DaoWorkspaceJpa;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
@Slf4j
public class WorkspaceAdapter implements WorkspaceRepository {

    private final DaoWorkspaceJpa workspaceJpa;
    private final DaoWorkspaceInfoJpa workspaceInfoJpa;
    private final DaoAuditLog daoAuditLog;
    private final DaoAdminJpa daoAdminJpa;

    @Override
    @Transactional
    public void insertNewWorkspace(GlobalModel globalModel) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        WorkspaceModel workspaceModel = new WorkspaceModel();
        workspaceModel.setWorkspaceId(globalModel.getWorkspaceId());
        workspaceModel.setName(globalModel.getWorkspaceName());

        Workspace workspace = WorkspaceMapper.toWorkspaceEntity(workspaceModel);
        log.debug("Workspace : [{}]", workspace);

        Workspace savedWorkspace = workspaceJpa.saveAndFlush(workspace);

        AuditLog auditLogOne = new AuditLog();
        auditLogOne.setTableName("workspace");
        auditLogOne.setPkValue(savedWorkspace.getWorkspaceId());
        auditLogOne.setAction(AccountEnum.HistoryType.INSERT.getValue());
        auditLogOne.setChangeAt(LocalDateTime.now());
        auditLogOne.setChangedBy(globalModel.getAdminId());
        auditLogOne.setChangedByName(daoAdminJpa.getAdminName(globalModel.getAdminId()));
        auditLogOne.setWorkspaceId(savedWorkspace.getWorkspaceId());

        String workspaceToJson = objectMapper.writeValueAsString(savedWorkspace);
        auditLogOne.setChangeJson("{\"from\":null,\"to\":" + workspaceToJson + "}");

        daoAuditLog.save(auditLogOne);

        WorkspaceInfoModel workspaceInfoModel = new WorkspaceInfoModel();
        workspaceInfoModel.setWorkspaceId(globalModel.getWorkspaceId());
        workspaceInfoModel.setHierarchy(globalModel.getWorkspaceHierarchy());
        workspaceInfoModel.setAdminId(globalModel.getAdminId());

        WorkspaceInfo workspaceInfo = WorkspaceInfoMapper.toWorkspaceInfoEntity(workspaceInfoModel);
        log.debug("WorkspaceInfo : [{}]", workspaceInfo);

        WorkspaceInfo savedWorkspaceInfo = workspaceInfoJpa.saveAndFlush(workspaceInfo);

        AuditLog auditLogTwo = new AuditLog();
        auditLogTwo.setTableName("workspace_info");
        auditLogTwo.setPkValue(String.valueOf(savedWorkspaceInfo.getId()));
        auditLogTwo.setAction(AccountEnum.HistoryType.INSERT.getValue());
        auditLogTwo.setChangeAt(LocalDateTime.now());
        auditLogTwo.setChangedBy(globalModel.getAdminId());
        auditLogTwo.setChangedByName(daoAdminJpa.getAdminName(globalModel.getAdminId()));
        auditLogTwo.setWorkspaceId(globalModel.getWorkspaceId());

        String workspaceInfoToJson = objectMapper.writeValueAsString(savedWorkspaceInfo);
        auditLogTwo.setChangeJson("{\"from\":null,\"to\":" + workspaceInfoToJson + "}");

        daoAuditLog.save(auditLogTwo);
    }

    @Override
    @Transactional
    public void addWorkspace(GlobalModel globalModel) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        WorkspaceInfoModel workspaceInfoModel = new WorkspaceInfoModel();
        workspaceInfoModel.setWorkspaceId(globalModel.getWorkspaceId());
        workspaceInfoModel.setHierarchy(globalModel.getWorkspaceHierarchy());
        workspaceInfoModel.setAdminId(globalModel.getAdminId());

        WorkspaceInfo workspaceInfo = WorkspaceInfoMapper.toWorkspaceInfoEntity(workspaceInfoModel);
        log.debug("WorkspaceInfo : [{}]", workspaceInfo);

        WorkspaceInfo savedWorkspaceInfo = workspaceInfoJpa.saveAndFlush(workspaceInfo);

        AuditLog auditLog = new AuditLog();
        auditLog.setTableName("workspace_info");
        auditLog.setPkValue(String.valueOf(savedWorkspaceInfo.getId()));
        auditLog.setAction(AccountEnum.HistoryType.INSERT.getValue());
        auditLog.setChangeAt(LocalDateTime.now());
        auditLog.setChangedBy(globalModel.getAdminId());
        auditLog.setChangedByName(daoAdminJpa.getAdminName(globalModel.getAdminId()));
        auditLog.setWorkspaceId(globalModel.getWorkspaceId());

        String toJson = objectMapper.writeValueAsString(savedWorkspaceInfo);
        auditLog.setChangeJson("{\"from\":null,\"to\":" + toJson + "}");

        daoAuditLog.save(auditLog);
    }

    @Override
    @Transactional
    public void editWorkspace(GlobalModel globalModel) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        Workspace workspace = workspaceJpa.findById(globalModel.getWorkspaceId())
                .orElseThrow(() -> new Exception("Data Not Found"));

        String fromJson = objectMapper.writeValueAsString(workspace);

        workspace.setName(globalModel.getWorkspaceName());
        log.debug("UPDATE WORKSPACE : [{}]", workspace);

        Workspace saved = workspaceJpa.saveAndFlush(workspace);

        String toJson = objectMapper.writeValueAsString(saved);

        AuditLog auditLog = new AuditLog();
        auditLog.setTableName("workspace");
        auditLog.setPkValue(saved.getWorkspaceId());
        auditLog.setAction(AccountEnum.HistoryType.UPDATE.getValue());
        auditLog.setChangeAt(LocalDateTime.now());
        auditLog.setChangedBy(globalModel.getAdminId());
        auditLog.setChangedByName(daoAdminJpa.getAdminName(globalModel.getAdminId()));
        auditLog.setWorkspaceId(saved.getWorkspaceId());

        auditLog.setChangeJson("{\"from\":" + fromJson + ",\"to\":" + toJson + "}");

        daoAuditLog.save(auditLog);
    }



}
