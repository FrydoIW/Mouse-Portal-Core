package com.example.back.backend.application.service.BRANCH;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.branch.BRO0300AInput;
import com.example.back.backend.application.dto.branch.BRO0300AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.infrastructure.jpa.DaoBranchJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : BRO0100ASVC
 * @author   : dodocool
 * @description : Delete Branch / Delete 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BRO0300ASVC {

    private final DaoBranchJpa branchJpa;

    public static class CtxSVC {

        BRO0300AInput input;
        BRO0300AOutput output;

    }

    public BRO0300AOutput execute(BRO0300AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new BRO0300AOutput();

        checkData(ctxSVC);
        deleteBranch(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void checkData(CtxSVC ctxSVC)throws IllegalArgumentException{

        long dataCount = branchJpa.count();

        if (dataCount == 1) {
            ctxSVC.output.setStatus("09");
            ctxSVC.output.setRemark("DATA ONLY ONE");
        }

        if (ctxSVC.input.getBranchId() == null) {
            throw new BizException(SysErrCode.DEL_ID_NULL);
        }

    }

    private void deleteBranch(CtxSVC ctxSVC) {

        if (ctxSVC.output.getStatus() != null && ctxSVC.output.getStatus().equals("09")) return;

        branchJpa.deleteById(ctxSVC.input.getBranchId());

    }

    private void putOutput(CtxSVC ctxSVC) {

        if (ctxSVC.output.getStatus() != null && ctxSVC.output.getStatus().equals("09")) return;

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA DELETED");

    }

}
