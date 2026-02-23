package com.example.back.backend.application.dto.history;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HIS0100AOutput {

    private int page;
    private int size;
    private long totalData;

    private List<Row> data;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Row {

        private long id;
        private LocalDateTime changeAt;

        private String workspaceId;

        private int adminId;
        private String adminName;

        private String actionType;
        private String tableName;
        private String pkValue;

        private String summary;

        private String changeJson;
    }
}