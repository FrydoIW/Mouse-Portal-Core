package com.example.back.backend.common.util;

import com.example.back.backend.domain.model.WorkspaceInfoModel;
import com.example.back.backend.domain.model.WorkspaceModel;
import com.example.back.backend.infrastructure.entity.PK.WorkspaceInfoPk;
import com.example.back.backend.infrastructure.entity.WorkspaceInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WorkspaceInfoMapper {


    public static WorkspaceInfo toWorkspaceInfoEntity (WorkspaceInfoModel workspaceInfoModel) {

        WorkspaceInfoPk workPk = new WorkspaceInfoPk();
        WorkspaceInfo workspaceInfo = new WorkspaceInfo();

        workPk.setWorkspaceId(workspaceInfoModel.getWorkspaceId());
        workPk.setAdminId(workspaceInfoModel.getAdminId());

        workspaceInfo.setId(workPk);
        workspaceInfo.setHierarchy(workspaceInfoModel.getHierarchy());
        workspaceInfo.setUpdDt(LocalDate.now());
        workspaceInfo.setRegDt(LocalDate.now());

        return workspaceInfo;

    }

}
