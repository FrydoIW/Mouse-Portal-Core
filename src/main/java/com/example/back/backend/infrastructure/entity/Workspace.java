package com.example.back.backend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workspace")
@ToString
public class Workspace {

    @Id
    @Column(name = "workspace_id")
    private String workspaceId;

    @Column(name = "name")
    private String name;

    @Column(name = "reg_dt")
    private LocalDate regDt;

    @Column(name = "upd_dt")
    private LocalDate updDt;

}
