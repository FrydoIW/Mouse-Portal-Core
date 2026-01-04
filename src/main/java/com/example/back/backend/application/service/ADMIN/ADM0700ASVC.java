package com.example.back.backend.application.service.ADMIN;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.admin.ADM0700AInput;
import com.example.back.backend.application.dto.admin.ADM0700AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.common.util.ValidatingOtp;
import com.example.back.backend.infrastructure.entity.Admin;
import com.example.back.backend.infrastructure.jpa.DaoAdminJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : ADM0700ASVC
 * @author   : dodocool
 * @description : Verification 2FA / Verify 🗿
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ADM0700ASVC {

    private final ValidatingOtp validatingOtp;

    private final DaoAdminJpa adminJpa;

    public static class CtxSVC {

        ADM0700AInput input;
        ADM0700AOutput output;

    }

    public ADM0700AOutput execute(ADM0700AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new ADM0700AOutput();

        TwoStepVerify(ctxSVC);

        return ctxSVC.output;

    }

    private void TwoStepVerify(CtxSVC ctxSVC) throws BizException {

        Admin admin = adminJpa.findAdminByEmailVerified(ctxSVC.input.getAdminEmail());

        if (admin == null){

            throw new BizException(SysErrCode.ACCOUNT_NOT_FOUND);

        }else{

            if (validatingOtp.isValid(admin.getTwoFactorSecret(),ctxSVC.input.getGoogleOtp())){

                ctxSVC.output.setStatus("00");
                ctxSVC.output.setRemark("SUCCESS OTP CORRECT");

            }else{

                ctxSVC.output.setStatus("09");
                ctxSVC.output.setRemark("FAILED OTP WRONG");

            }

        }

    }



}
