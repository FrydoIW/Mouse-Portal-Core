package com.example.back.backend.common.util;

import com.example.back.backend.domain.model.WorkspaceModel;
import com.example.back.backend.infrastructure.entity.Workspace;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WorkspaceMapper {

    public static Workspace toWorkspaceEntity (WorkspaceModel model){

        Workspace workspace = new Workspace();

        workspace.setWorkspaceId(model.getWorkspaceId());
        workspace.setName(model.getName());
        workspace.setRegDt(LocalDate.now());
        workspace.setUpdDt(LocalDate.now());

        return workspace;
    }

}
