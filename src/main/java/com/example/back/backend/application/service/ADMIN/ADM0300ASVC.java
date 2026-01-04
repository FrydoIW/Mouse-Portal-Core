package com.example.back.backend.application.service.ADMIN;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.admin.ADM0300AInput;
import com.example.back.backend.application.dto.admin.ADM0300AOutput;
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
 * @fileName : ATM0300ASC
 * @author   : dodocool
 * @description : Change Password / ChangePass 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ADM0300ASVC {

    private final DaoAdminJpa adminJpa;
    private final AdminRepository adminRepository;

    public static class CtxSVC {
        Admin admin;
        String passwordHash;
        ADM0300AInput input;
        ADM0300AOutput output;
    }

    public ADM0300AOutput execute(ADM0300AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ADM0300AOutput();

        checkInput(ctxSVC);
        createPassHash(ctxSVC);
        editPassword(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;

    }

    private void checkInput(CtxSVC ctxSVC) throws BizException {

        if (ctxSVC.input.getAdminEmail().isEmpty()){

            throw new BizException(SysErrCode.EMAIL_INPUT_NULL);

        }else{

            ctxSVC.admin = adminJpa.findAdminByEmailVerified(ctxSVC.input.getAdminEmail());

            if (ctxSVC.admin == null) {

                throw new BizException(SysErrCode.EMAIL_NOT_FOUNT);

            }

        }

    }

    private void createPassHash(CtxSVC ctxSVC){

        ctxSVC.passwordHash = PasswordUtil.hashPassword(ctxSVC.input.getNewPassword());

    }

    private void editPassword(CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();

        globalModel.setAdminEmail(ctxSVC.admin.getEmail());
        globalModel.setAdminPasswordHash(ctxSVC.passwordHash);

        adminRepository.editPassword(globalModel);

    }

    public void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("PASSWORD SUCCESS CHANGED");

    }


}
