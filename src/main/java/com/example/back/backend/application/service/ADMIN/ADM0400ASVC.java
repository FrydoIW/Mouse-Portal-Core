package com.example.back.backend.application.service.ADMIN;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.admin.ADM0400AInput;
import com.example.back.backend.application.dto.admin.ADM0400AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.common.util.PasswordUtil;
import com.example.back.backend.infrastructure.entity.Admin;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : ADM0400ASVC
 * @author   : dodocool
 * @description : Verification Password / Verification Pass 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ADM0400ASVC {

    private final DaoAdminJpa adminJpa;

    public static class CtxSVC {
        String passwordHash;
        ADM0400AInput input;
        ADM0400AOutput output;

    }

    public ADM0400AOutput execute(ADM0400AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ADM0400AOutput();

        inquiryAccount(ctxSVC);
        validatingPassword(ctxSVC);

        return ctxSVC.output;

    }

    private void inquiryAccount(CtxSVC ctxSVC) throws BizException {

        Admin admin = adminJpa.findAdminByEmailVerified(ctxSVC.input.getEmail());

        if (admin == null) {

            throw new BizException(SysErrCode.ACCOUNT_NOT_FOUND);

        }else {

            ctxSVC.passwordHash = admin.getPasswordHash();

        }

    }

    private void validatingPassword(CtxSVC ctxSVC) throws Exception {

        if (PasswordUtil.verifyPassword(ctxSVC.input.getPassword(),ctxSVC.passwordHash)){

            ctxSVC.output.setStatus("00");
            ctxSVC.output.setRemark("PASSWORD SUCCESS VERIFY");

        }else{

            ctxSVC.output.setStatus("09");
            ctxSVC.output.setRemark("PASSWORD WRONG");

        }

    }

}
