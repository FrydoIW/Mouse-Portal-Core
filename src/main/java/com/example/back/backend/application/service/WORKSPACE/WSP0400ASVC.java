package com.example.back.backend.application.service.WORKSPACE;

import com.example.back.backend.application.dto.workspace.WSP0400AInput;
import com.example.back.backend.application.dto.workspace.WSP0400AOutput;
import com.example.back.backend.infrastructure.entity.PK.WorkspaceInfoPk;
import com.example.back.backend.infrastructure.jpa.DaoWorkspaceInfoJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class WSP0400ASVC {

    private final DaoWorkspaceInfoJpa workspaceInfoJpa;

    private static class CtxSVC {
        WSP0400AInput input;
        WSP0400AOutput output;

    }

    public WSP0400AOutput execute(WSP0400AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new WSP0400AOutput();

        deleteProcess(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;
    }

    public void deleteProcess(CtxSVC ctxSVC) throws Exception {

        WorkspaceInfoPk workspaceInfoPk = new WorkspaceInfoPk();
        workspaceInfoPk.setWorkspaceId(ctxSVC.input.getWorkspaceId());
        workspaceInfoPk.setAdminId(ctxSVC.input.getAdminId());

        workspaceInfoJpa.deleteById(workspaceInfoPk);

    }

    public void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("SUCCESS DELETE LINKED WORKSPACE");

    }

}
