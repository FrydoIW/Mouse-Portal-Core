package com.example.back.backend.application.service;


import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.TKD0200AInput;
import com.example.back.backend.application.dto.TKD0200AOutput;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.common.util.ValidatingOtp;
import com.example.back.backend.infrastructure.persistence.ValidatingAuthJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @fileName : TKD0200ASVC
 * @author   : dodocool
 * @description : validating email & password / Auth Process 🗿
 */


@Slf4j
@Service
@RequiredArgsConstructor
public class TKD0200ASVC {

    private final ValidatingAuthJpa validatingAuthJpa;
    private final ValidatingOtp validatingOtp;

    private static class CtxSVC{

        TKD0200AInput input;
        TKD0200AOutput output;

    }

    public TKD0200AOutput execute(TKD0200AInput input) throws Exception {

        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0200AOutput();

        checkAuthProcess(ctxSVC);
        putOutput(ctxSVC);

        return ctxSVC.output;
    }

    private void checkAuthProcess(CtxSVC ctxSVC) throws BizException {

        if(!validatingAuthJpa.isValidLogin(ctxSVC.input.getEmail(),ctxSVC.input.getPassword())){
            throw new BizException(SysErrCode.PASSWORD_INVALID);
        }

        if (ctxSVC.input.isVerifyOtp()){

            if (!validatingOtp.isValid(validatingAuthJpa.getSecretKey(ctxSVC.input.getEmail(),ctxSVC.input.getPassword()),ctxSVC.input.getOtp())){
                throw new BizException(SysErrCode.INVALID_OTP);
            }
        }

    }

    private void putOutput(CtxSVC ctxSVC) throws Exception {

        ctxSVC.output.setStatus("00");
        ctxSVC.output.setRemark("Validating Auth Complete");

    }

}
