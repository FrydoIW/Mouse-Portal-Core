package com.example.back.backend.application.service.ADMIN;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.admin.ADM0500AInput;
import com.example.back.backend.application.dto.admin.ADM0500AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.common.util.CompareUtil;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AdminRepository;
import com.example.back.backend.infrastructure.entity.Admin;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : ADM0500ASVC
 * @author   : dodocool
 * @description : Edit admin Profiles / Edit 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ADM0500ASVC {

    private final DaoAdminJpa adminJpa;

    private final AdminRepository adminRepository;

    public static class CtxSVC {
        Admin admin;
        ADM0500AInput input;
        ADM0500AOutput output;

    }

    public ADM0500AOutput execute(ADM0500AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ADM0500AOutput();

        inquiryAccount(ctxSVC);
        editAdminData(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void inquiryAccount(CtxSVC ctxSVC) throws Exception {

        ctxSVC.admin = adminJpa.findById(ctxSVC.input.getId())
                .orElseThrow(() -> new BizException(SysErrCode.ACCOUNT_NOT_FOUND));
    }

    private void editAdminData(CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();

        globalModel.setAdminId(ctxSVC.input.getId());
        globalModel.setAdminName(CompareUtil.getValueOrDefault(ctxSVC.input.getName(),ctxSVC.admin.getName()));
        globalModel.setAdminGender(CompareUtil.getValueOrDefault(ctxSVC.input.getGender(),ctxSVC.admin.getGender()));
        globalModel.setAdminBrithDt(CompareUtil.getValueOrDefault(ctxSVC.input.getBirthDt(),ctxSVC.admin.getBirthDt()));

        adminRepository.editAdminAccount(globalModel);

    }

    private void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setRemark("SUCCESS EDIT ADMIN PROFILE");
        ctxSVC.output.setStatus("00");

    }

}
