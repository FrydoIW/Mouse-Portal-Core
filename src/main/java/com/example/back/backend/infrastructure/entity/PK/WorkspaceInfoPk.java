package com.example.back.backend.infrastructure.entity.PK;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class WorkspaceInfoPk implements Serializable {

    @Column(name = "workspace_id")
    private String workspaceId;
    @Column(name = "admin_id")
    private int adminId;

}
