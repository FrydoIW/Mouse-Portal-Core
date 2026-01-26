package com.example.back.backend.infrastructure.adapter;

import com.example.back.backend.common.util.WorkspaceInfoMapper;
import com.example.back.backend.common.util.WorkspaceMapper;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.model.WorkspaceInfoModel;
import com.example.back.backend.domain.model.WorkspaceModel;
import com.example.back.backend.domain.repository.WorkspaceRepository;
import com.example.back.backend.infrastructure.entity.Workspace;
import com.example.back.backend.infrastructure.entity.WorkspaceInfo;
import com.example.back.backend.infrastructure.jpa.DaoWorkspaceInfoJpa;
import com.example.back.backend.infrastructure.jpa.DaoWorkspaceJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Slf4j
public class WorkspaceAdapter implements WorkspaceRepository {

    private final DaoWorkspaceJpa workspaceJpa;
    private final DaoWorkspaceInfoJpa workspaceInfoJpa;

    @Override
    public void insertNewWorkspace(GlobalModel globalModel) throws Exception {

        WorkspaceModel workspaceModel = new WorkspaceModel();

        workspaceModel.setWorkspaceId(globalModel.getWorkspaceId());
        workspaceModel.setName(globalModel.getWorkspaceName());

        Workspace workspace = WorkspaceMapper.toWorkspaceEntity(workspaceModel);

        log.debug("Workspace : [{}]", workspace);

        workspaceJpa.save(workspace);

        WorkspaceInfoModel workspaceInfoModel = new WorkspaceInfoModel();

        workspaceInfoModel.setWorkspaceId(globalModel.getWorkspaceId());
        workspaceInfoModel.setHierarchy(globalModel.getWorkspaceHierarchy());
        workspaceInfoModel.setAdminId(globalModel.getAdminId());

        WorkspaceInfo workspaceInfo = WorkspaceInfoMapper.toWorkspaceInfoEntity(workspaceInfoModel);

        log.debug("WorkspaceInfo : [{}]",workspaceInfo);

        workspaceInfoJpa.save(workspaceInfo);

    }

    @Override
    public void addWorkspace(GlobalModel globalModel) throws Exception {

        WorkspaceInfoModel workspaceInfoModel = new WorkspaceInfoModel();

        workspaceInfoModel.setWorkspaceId(globalModel.getWorkspaceId());
        workspaceInfoModel.setHierarchy(globalModel.getWorkspaceHierarchy());
        workspaceInfoModel.setAdminId(globalModel.getAdminId());

        WorkspaceInfo workspaceInfo = WorkspaceInfoMapper.toWorkspaceInfoEntity(workspaceInfoModel);

        log.debug("WorkspaceInfo : [{}]",workspaceInfo);

        workspaceInfoJpa.save(workspaceInfo);

    }

    @Override
    public void editWorkspace(GlobalModel globalModel) throws Exception {

        Workspace workspace = workspaceJpa.findById(globalModel.getWorkspaceId()).orElseThrow(() -> new Exception("Data Not Found"));

        workspace.setName(globalModel.getWorkspaceName());

        log.debug("UPDATE WORKSPACE : [{}]",workspace);

        workspaceJpa.save(workspace);
    }



}
