package com.example.back.backend.application.service.WORKSPACE;

import com.example.back.backend.application.dto.workspace.WSP0200AInput;
import com.example.back.backend.application.dto.workspace.WSP0200AOutput;
import com.example.back.backend.common.util.CompareUtil;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.WorkspaceRepository;
import com.example.back.backend.infrastructure.entity.Workspace;
import com.example.back.backend.infrastructure.jpa.DaoWorkspaceJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : WSP0200ASVC
 * @author   : dodocool
 * @description : Edit workspace / Edit workspace account 🗿
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class WSP0200ASVC {

    private final DaoWorkspaceJpa workspaceJpa;
    private final WorkspaceRepository workspaceRepository;

    public static class CtxSVC {
        Workspace workspace;
        WSP0200AInput input;
        WSP0200AOutput output;
    }

    public WSP0200AOutput execute(WSP0200AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new WSP0200AOutput();

        checkInput(ctxSVC);
        setWorkspaceJpa(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;
    }

    public void checkInput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.workspace = workspaceJpa.findById(ctxSVC.input.getWorkspaceId()).orElseThrow(() -> new Exception("Data Not Found"));

    }

    public void setWorkspaceJpa(CtxSVC ctxSVC) throws Exception{

        GlobalModel globalModel = new GlobalModel();
        globalModel.setWorkspaceId(ctxSVC.input.getWorkspaceId());
        globalModel.setWorkspaceName(CompareUtil.getValueOrDefault(ctxSVC.input.getWorkspaceName(),ctxSVC.workspace.getName()));

        workspaceRepository.editWorkspace(globalModel);

    }

    public void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("Success Edit Workspace");

    }

}
