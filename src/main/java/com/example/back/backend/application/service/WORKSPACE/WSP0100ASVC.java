package com.example.back.backend.application.service.WORKSPACE;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.workspace.WSP0100AInput;
import com.example.back.backend.application.dto.workspace.WSP0100AOutput;
import com.example.back.backend.common.Enum.AccountEnum;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.WorkspaceRepository;
import com.example.back.backend.infrastructure.entity.Admin;
import com.example.back.backend.infrastructure.entity.Workspace;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import com.example.back.backend.infrastructure.jpa.DaoWorkspaceJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : WSP0100ASVC
 * @author   : dodocool
 * @description : Invite workspace / Invite workspace account 🗿
 */


@Slf4j
@RequiredArgsConstructor
@Service
public class WSP0100ASVC {

    private final DaoWorkspaceJpa workspaceJpa;
    private final DaoAdminJpa adminJpa;
    private final WorkspaceRepository workspaceRepository;

    public static class CtxSVC {
        Workspace workspace;
        Admin admin;
        WSP0100AInput input;
        WSP0100AOutput output;
    }

    public WSP0100AOutput execute(WSP0100AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new WSP0100AOutput();

        checkInput(ctxSVC);
        insertWorkspace(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void checkInput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.workspace = workspaceJpa.findById(ctxSVC.input.getWorkspaceId()).orElseThrow(() -> new Exception("Data Not Found"));

        ctxSVC.admin = adminJpa.findAdminByEmailVerified(ctxSVC.input.getEmail());

        if (ctxSVC.admin == null) {
            throw new BizException(SysErrCode.ACCOUNT_NOT_FOUND);
        }

    }

    private void insertWorkspace(CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();

        globalModel.setWorkspaceId(ctxSVC.workspace.getWorkspaceId());
        globalModel.setAdminId(ctxSVC.admin.getId());
        globalModel.setWorkspaceHierarchy(AccountEnum.workspace.CHILD.getValue());

        workspaceRepository.addWorkspace(globalModel);

    }

    private void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("Success add new workspace");

    }

}