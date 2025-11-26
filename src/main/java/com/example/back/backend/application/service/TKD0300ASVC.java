package com.example.back.backend.application.service;

import com.example.back.backend.Exception.BizException;
import com.example.back.backend.application.dto.TKD0300AInput;
import com.example.back.backend.application.dto.TKD0300AOutput;
import com.example.back.backend.common.Enum.AccountEnum;
import com.example.back.backend.common.Enum.SysErrCode;
import com.example.back.backend.infrastructure.jpa.DaoMemberJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TKD0300ASVC {

    private final DaoMemberJpa daoMemberJpa;

    private static class CtxSVC{

        TKD0300AInput input;
        TKD0300AOutput output;

    }

    public TKD0300AOutput execute(TKD0300AInput input) throws Exception {


        CtxSVC ctxSVC = new CtxSVC();
        ctxSVC.input = input;
        ctxSVC.output = new TKD0300AOutput();

        decider(ctxSVC);

        return ctxSVC.output;

    }

    private void decider(CtxSVC ctxSVC) throws Exception{

            if (ctxSVC.input.getProcType().equals(AccountEnum.AuthProcType.CHECK_EMAIL.getValue())){
                emailVerification(ctxSVC);
            } else {
                passwordChanges(ctxSVC);
            }

    }

    private void emailVerification(CtxSVC ctxSVC) throws Exception {

        if(daoMemberJpa.existsByEmail(ctxSVC.input.getEmail())){
            throw new BizException(SysErrCode.EMAIL_NOT_FOUNT);
        }else{
            ctxSVC.output.setStatus("00");
            ctxSVC.output.setRemark("Email Correct");
        }

    }

    private void passwordChanges(CtxSVC ctxSVC) throws Exception {

        log.debug("To Be Continue : ");

    }

}
