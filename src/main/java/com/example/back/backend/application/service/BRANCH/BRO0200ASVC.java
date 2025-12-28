package com.example.back.backend.application.service.BRANCH;


import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.branch.BRO0200AInput;
import com.example.back.backend.application.dto.branch.BRO0200AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.BranchRepository;
import com.example.back.backend.infrastructure.entity.Branch;
import com.example.back.backend.infrastructure.jpa.DaoBranchJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @fileName : BRO0100ASVC
 * @author   : dodocool
 * @description : Edit Branch / Edit 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BRO0200ASVC {

    private final DaoBranchJpa branchJpa;

    private final BranchRepository branchRepository;

    public static class CtxSVC {

        Branch branch;
        BRO0200AInput input;
        BRO0200AOutput output;

    }

    public BRO0200AOutput execute(BRO0200AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new BRO0200AOutput();

        checkInput(ctxSVC);
        editData(ctxSVC);
        putOutput(ctxSVC);


        return ctxSVC.output;

    }

    public void checkInput(CtxSVC ctxSVC) throws Exception {

        if (ctxSVC.input.getBranchId() == null) {
            throw new BizException(SysErrCode.EDIT_ID_NULL);
        }

        ctxSVC.branch = branchJpa.findById(ctxSVC.input.getBranchId()).orElseThrow(() -> new Exception("Data Not Found")); ;

    }

    private void editData(CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();

        globalModel.setBranchId(ctxSVC.input.getBranchId());
        globalModel.setBranchName(getValueOrDefault(ctxSVC.input.getBranchName(),ctxSVC.branch.getBranchName()));

        branchRepository.editBranch(globalModel);

    }

    private void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("ALL DATA EDITED");

    }

    private <T> T getValueOrDefault(T inputValue, T defaultValue) {
        if (inputValue == null) {
            return defaultValue;
        }

        if (inputValue instanceof String) {
            String str = (String) inputValue;
            if (str.trim().isEmpty()) {
                return defaultValue;
            }
        }

        if (inputValue instanceof BigDecimal) {
            BigDecimal bd = (BigDecimal) inputValue;
            if (bd.compareTo(BigDecimal.ZERO) == 0) {
                return defaultValue;
            }
        }

        return inputValue;
    }




}
