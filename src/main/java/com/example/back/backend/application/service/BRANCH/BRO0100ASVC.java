package com.example.back.backend.application.service.BRANCH;

import com.example.back.backend.application.dto.branch.BRO0100AInput;
import com.example.back.backend.application.dto.branch.BRO0100AOutput;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : BRO0100ASVC
 * @author   : dodocool
 * @description : Add Branch / Add branch 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BRO0100ASVC {

    private final BranchRepository branchRepository;

    public class CtxSVC {
        BRO0100AInput input;
        BRO0100AOutput output;
    }

    public BRO0100AOutput execute (BRO0100AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new BRO0100AOutput();

        registerBranch(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void registerBranch(CtxSVC ctxSVC){

        GlobalModel globalModel = new GlobalModel();

        globalModel.setBranchName(ctxSVC.input.getBranchName());
        globalModel.setWorkspaceId(ctxSVC.input.getWorkspaceId());

        branchRepository.insertBranchInfo(globalModel);

    }

    private void putOutput(CtxSVC ctxSVC) {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA INSERTED");

    }


}
