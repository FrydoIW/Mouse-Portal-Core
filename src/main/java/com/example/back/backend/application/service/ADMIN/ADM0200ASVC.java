package com.example.back.backend.application.service.ADMIN;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.admin.ADM0200AInput;
import com.example.back.backend.application.dto.admin.ADM0200AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.common.util.GenereateSecretKey;
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
 * @description : Registration Two Factor Auth / Register QR CODE 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ADM0200ASVC {

    private final DaoAdminJpa adminJpa;
    private final GenereateSecretKey generateSecretKey;
    private final AdminRepository adminRepository;

    public static class CtxSVC{
        String secret;
        String qrBase64;
        ADM0200AInput input;
        ADM0200AOutput output;

    }

    public ADM0200AOutput execute(ADM0200AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ADM0200AOutput();

        validatingUser(ctxSVC);
        generateTwoFactorSecret(ctxSVC);
        insertToDatabase(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;
    }

    private void validatingUser(CtxSVC ctxSVC) {

        Admin admin = adminJpa.findAdminByEmailVerified(ctxSVC.input.getAdminEmail());

        if (admin != null) {

            throw new BizException(SysErrCode.USER_FOUNT);

        }

    }

    private void generateTwoFactorSecret(CtxSVC ctxSVC){

        ctxSVC.secret = generateSecretKey.generateSecret();
        ctxSVC.qrBase64 = generateSecretKey.generateQrBase64(ctxSVC.input.getAdminEmail(), ctxSVC.secret);

    }

    private void insertToDatabase(CtxSVC ctxSVC) throws Exception {

        GlobalModel globalModel = new GlobalModel();

        globalModel.setAdminTwoFactorSecret(ctxSVC.secret);
        globalModel.setAdminEmail(ctxSVC.input.getAdminEmail());

        adminRepository.insertAdmin2FA(globalModel);

    }

    private void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("SUCCESS REGISTER ADMIN 2FA");
        ctxSVC.output.setQrBase64(ctxSVC.qrBase64);

    }

}
