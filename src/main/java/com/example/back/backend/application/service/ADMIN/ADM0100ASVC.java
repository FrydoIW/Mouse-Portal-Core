package com.example.back.backend.application.service.ADMIN;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.admin.ADM0100AInput;
import com.example.back.backend.application.dto.admin.ADM0100AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.common.util.PasswordUtil;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AdminRepository;
import com.example.back.backend.infrastructure.entity.Admin;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : ATM0200ASVC
 * @author   : dodocool
 * @description : Registration Admin / Register account 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ADM0100ASVC {

    private final DaoAdminJpa adminJpa;

    private final AdminRepository adminRepository;

    public static class CtxSVC {
        String hashPassword;
        ADM0100AInput input;
        ADM0100AOutput output;

    }

    public ADM0100AOutput execute(ADM0100AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ADM0100AOutput();

        checkExistingAdmin(ctxSVC);
        encryptPassword(ctxSVC);
        createNewAccount(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void checkExistingAdmin(CtxSVC ctxSVC) throws Exception {

        Admin admin = adminJpa.findAdminByEmailVerified(ctxSVC.input.getEmail());

        if (admin != null) {

            throw new BizException(SysErrCode.USER_FOUNT);

        }

    }

    private void encryptPassword(CtxSVC ctxSVC) throws Exception {

        ctxSVC.hashPassword = PasswordUtil.hashPassword(ctxSVC.input.getPassword());

    }

    private void createNewAccount(CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();
        globalModel.setAdminName(ctxSVC.input.getName());
        globalModel.setAdminAddress(ctxSVC.input.getAddress());
        globalModel.setAdminBrithDt(ctxSVC.input.getBirthDt());
        globalModel.setAdminGender(ctxSVC.input.getGender());
        globalModel.setAdminEmail(ctxSVC.input.getEmail());
        globalModel.setAdminPasswordHash(ctxSVC.hashPassword);

        adminRepository.insertAdminRegis(globalModel);

    }

    private void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("SUCCESS REGISTER ADMIN");

    }



}
