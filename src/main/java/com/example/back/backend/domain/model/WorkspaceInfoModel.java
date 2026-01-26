package com.example.back.backend.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkspaceInfoModel {

    String workspaceId;
    int adminId;
    String hierarchy;

}
