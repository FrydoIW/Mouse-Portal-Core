package com.example.back.backend.application.service.EMAIL;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.email.EMA0200AInput;
import com.example.back.backend.application.dto.email.EMA0200AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.domain.model.GlobalModel;
import com.example.back.backend.domain.repository.AdminRepository;
import com.example.back.backend.infrastructure.entity.Admin;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : EMA0200ASVC
 * @author   : dodocool
 * @description : Email Verification / Email Verification 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class EMA0200ASVC {

    private final DaoAdminJpa adminJpa;

    private final AdminRepository adminRepository;

    public static class CtxSVC {

        EMA0200AInput input;
        EMA0200AOutput output;

    }

    public EMA0200AOutput execute(EMA0200AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new EMA0200AOutput();

        checkInput(ctxSVC);
        verifyEmail(ctxSVC);

        return ctxSVC.output;

    }

    public void checkInput(CtxSVC ctxSVC) throws BizException {

        if (ctxSVC.input.getToken() == null) {

            throw new BizException(SysErrCode.TOKEN_NOT_FOUND);

        }

    }

    private void verifyEmail(CtxSVC ctxSVC) throws Exception {

        Admin admin = adminJpa.findByVerificationToken(ctxSVC.input.getToken());

        log.debug("Token : [{}]",ctxSVC.input.getToken());

        GlobalModel globalModel = new GlobalModel();

        if (admin != null){

            globalModel.setAdminEmail(admin.getEmail());
            globalModel.setEmailVerification(true);
            globalModel.setVerificationToken(null);

            adminRepository.insertTokenVerificaiton(globalModel);

            ctxSVC.output.setStatus("00");
            ctxSVC.output.setRemark("SUCCESS VERIFY EMAIL");

        }else{

            ctxSVC.output.setStatus("09");
            ctxSVC.output.setRemark("FAILED VERIFY EMAIL");

        }

    }

}
