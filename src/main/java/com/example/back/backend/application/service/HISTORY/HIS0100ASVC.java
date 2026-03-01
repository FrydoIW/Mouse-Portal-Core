package com.example.back.backend.application.service.HISTORY;

import com.example.back.backend.application.dto.history.HIS0100AInput;
import com.example.back.backend.application.dto.history.HIS0100AOutput;
import com.example.back.backend.infrastructure.entity.AuditLog;
import com.example.back.backend.infrastructure.jpa.DaoAuditLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @fileName : HIS0100ASVC
 * @author   : dodocool
 * @description : Gather data history / History 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class HIS0100ASVC {

    private final DaoAuditLog daoAuditLog;

    private static class CtxSVC {

        HIS0100AInput input;
        HIS0100AOutput output;

    }

    public HIS0100AOutput execute (HIS0100AInput input) {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new HIS0100AOutput();

        return ctxSVC.output;

    }

    private void inquiryData(CtxSVC ctxSVC) throws Exception {

        int page = ctxSVC.input.getPage();
        int size = ctxSVC.input.getSize();
        int offset = page * size;

        LocalDateTime fromDt = (ctxSVC.input.getFromDt() == null)
                ? null
                : ctxSVC.input.getFromDt().atStartOfDay();

        LocalDateTime toDtExclusive = (ctxSVC.input.getToDt() == null)
                ? null
                : ctxSVC.input.getToDt().plusDays(1).atStartOfDay();

        long total = daoAuditLog.countHistoryData(
                ctxSVC.input.getWorkspaceId(),
                ctxSVC.input.getAdminId(),
                ctxSVC.input.getActionType(),
                ctxSVC.input.getTableName(),
                fromDt,
                toDtExclusive
        );

        List<AuditLog> rows = daoAuditLog.getHistoryData(
                ctxSVC.input.getWorkspaceId(),
                ctxSVC.input.getAdminId(),
                ctxSVC.input.getActionType(),
                ctxSVC.input.getTableName(),
                fromDt,
                toDtExclusive,
                size,
                offset
        );

        List<HIS0100AOutput.Row> outRows = new ArrayList<>();
        for (AuditLog al : rows) {
            HIS0100AOutput.Row r = new HIS0100AOutput.Row();
            r.setId(al.getId());
            r.setChangeAt(al.getChangedAt());
            r.setWorkspaceId(al.getWorkspaceId());

            r.setAdminId(al.getChangedBy());
            r.setAdminName(al.getChangedByName());

            r.setActionType(al.getAction());
            r.setTableName(al.getTableName());
            r.setPkValue(al.getPkValue());

            r.setSummary(al.getAction() + " " + al.getTableName() + " (" + al.getPkValue() + ")");
            r.setChangeJson(al.getChangesJson());

            outRows.add(r);
        }

        ctxSVC.output.setPage(page);
        ctxSVC.output.setSize(size);
        ctxSVC.output.setTotalData(total);
        ctxSVC.output.setData(outRows);
    }



}
