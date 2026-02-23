package com.example.back.backend.application.dto.history;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HIS0100AInput {

    String workspaceId;
    int adminId;
    String actionType;
    String tableName;

    private int page;
    private int size;

    private LocalDate fromDt;
    private LocalDate toDt;

}
