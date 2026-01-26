package com.example.back.backend.infrastructure.entity;

import com.example.back.backend.infrastructure.entity.PK.WorkspaceInfoPk;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workspace_info")
@ToString
public class WorkspaceInfo {

    @EmbeddedId
    private WorkspaceInfoPk id;

    @Column(name = "hierarchy")
    private String hierarchy;

    @Column(name = "reg_dt")
    private LocalDate regDt;

    @Column(name = "upd_dt")
    private LocalDate updDt;

}
